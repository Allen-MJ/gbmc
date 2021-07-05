package allen.frame.tools.HtmlImageUtil;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

import java.util.HashSet;
import java.util.Set;

import allen.frame.R;
import allen.frame.tools.Logger;

public class GlideImageGetter implements Html.ImageGetter, Drawable.Callback {
	private final Context mContext;

	private final TextView mTextView;

	private final Set<ImageGetterViewTarget> mTargets;

	public static GlideImageGetter get(View view) {
		return (GlideImageGetter) view.getTag(R.id.drawable_tag);
	}

	public void clear() {
		GlideImageGetter prev = get(mTextView);
		if (prev == null)
			return;

		for (ImageGetterViewTarget target : prev.mTargets) {
			Glide.clear(target);
		}
	}

	public GlideImageGetter(Context context, TextView textView) {
		this.mContext = context;
		this.mTextView = textView;

		mTargets = new HashSet<>();
		mTextView.setTag(R.id.drawable_tag, this);
	}

	@Override
	public Drawable getDrawable(String url) {
		final UrlDrawableGlide urlDrawable = new UrlDrawableGlide();
		Logger.e("html图片地址：", url);
//		MainUtil.printLogger("Downloading from: " + url);
		Glide.with(mContext).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
				.into(new ImageGetterViewTarget(mTextView, urlDrawable));
		return urlDrawable;
	}

	@Override
	public void invalidateDrawable(Drawable who) {
		mTextView.invalidate();
	}

	@Override
	public void scheduleDrawable(Drawable who, Runnable what, long when) {

	}

	@Override
	public void unscheduleDrawable(Drawable who, Runnable what) {

	}

	private class ImageGetterViewTarget extends ViewTarget<TextView, GlideDrawable> {

		private final UrlDrawableGlide mDrawable;

		private ImageGetterViewTarget(TextView view, UrlDrawableGlide drawable) {
			super(view);
			mTargets.add(this);
			this.mDrawable = drawable;
		}

		@Override
		public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
			Rect rect;
			if (resource.getIntrinsicWidth() > 100) {
				float width;
				float height;
				// MainUtil.printLogger("Image width is " +
				// resource.getIntrinsicWidth());
				// MainUtil.printLogger("View width is " + view.getWidth());
				if (resource.getIntrinsicWidth() >= getView().getWidth()) {
					float downScale = (float) resource.getIntrinsicWidth() / getView().getWidth();
					width = (float) resource.getIntrinsicWidth() / (float) downScale;
					height = (float) resource.getIntrinsicHeight() / (float) downScale;
				} else {
					width = (float) resource.getIntrinsicWidth();
					height = (float) resource.getIntrinsicHeight();
//					float multiplier = (float) getView().getWidth() / resource.getIntrinsicWidth();
//					width = (float) resource.getIntrinsicWidth() * (float) multiplier;
//					height = (float) resource.getIntrinsicHeight() * (float) multiplier;
				}
				rect = new Rect(0, 0, Math.round(width), Math.round(height));
			} else {
				rect = new Rect(0, 0, resource.getIntrinsicWidth() * 2, resource.getIntrinsicHeight() * 2);
			}
			resource.setBounds(rect);

			mDrawable.setBounds(rect);
			mDrawable.setDrawable(resource);

			if (resource.isAnimated()) {
				mDrawable.setCallback(get(getView()));
				resource.setLoopCount(GlideDrawable.LOOP_FOREVER);
				resource.start();
			}

			getView().setText(getView().getText());
			getView().invalidate();
		}

		private Request request;

		@Override
		public Request getRequest() {
			return request;
		}

		@Override
		public void setRequest(Request request) {
			this.request = request;
		}
	}

}
