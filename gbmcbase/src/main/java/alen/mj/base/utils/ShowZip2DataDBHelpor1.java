package alen.mj.base.utils;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import alen.mj.base.dao.impl.A01DaoImpl;
import alen.mj.base.dao.impl.A02DaoImpl;
import alen.mj.base.dao.impl.A05DaoImpl;
import alen.mj.base.dao.impl.A06DaoImpl;
import alen.mj.base.dao.impl.A08DaoImpl;
import alen.mj.base.dao.impl.A14DaoImpl;
import alen.mj.base.dao.impl.A15DaoImpl;
import alen.mj.base.dao.impl.A36DaoImpl;
import alen.mj.base.dao.impl.A57DaoImpl;
import alen.mj.base.dao.impl.A99Z1DaoImpl;
import alen.mj.base.dao.impl.B01DaoImpl;
import alen.mj.base.dao.impl.GwyinfoDaoImpl;
import alen.mj.base.dao.impl.M02DaoImpl;
import alen.mj.base.dao.impl.M03DaoImpl;
import alen.mj.base.entry.A01;
import alen.mj.base.entry.A02;
import alen.mj.base.entry.A05;
import alen.mj.base.entry.A06;
import alen.mj.base.entry.A08;
import alen.mj.base.entry.A14;
import alen.mj.base.entry.A15;
import alen.mj.base.entry.A36;
import alen.mj.base.entry.A57;
import alen.mj.base.entry.A99Z1;
import alen.mj.base.entry.B01;
import alen.mj.base.entry.M02;
import alen.mj.base.entry.M03;
import alen.mj.base.entry.gwyinfo;

/**
 * zip 转化为  插入 数据库  数据
 * @author Administrator
 *
 */
public class ShowZip2DataDBHelpor1 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sdcard_path = Environment.getExternalStorageDirectory().getPath();
	private String key_password = "20171020";
//	private String srcPath = sdcard_path + "/Download/XML20171127141145.zip";
	private String destPath = sdcard_path + "/GBMC_GWYINFOR/";
	
	private Context context;
	private String B0111_key = "-1";
	
	public ShowZip2DataDBHelpor1(Context context){
		 this.context = context;
		 handlerThread = new HandlerThread("loadDataThread");
		 handlerThread.start();
	}
	
	/**
	 * 处理数据加载
	 * @param handler
	 * @param files
	 */
	public void doUnZip2AnalysisData(final Handler handler,final List<String> files){
		 mHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
				@Override
				public boolean handleMessage(Message msg) {
					//在子线程中进行网络请求
					 int len = files.size();
	                 for(int i = 0;i < len; i ++){
	                	 singleZipFile(i+1, files.get(i), handler);
	                 }
	                 //通知主线程去更新UI
	                 Message msg1 = new Message();
	               	 msg1.what = 10;
	               	 msg1.obj = "数据包处理更新完成!";
	               	 handler.sendMessage(msg1);
					return false;
				}
			});
		 mHandler.sendEmptyMessage(0);
	}
	
	
	private HandlerThread handlerThread;
	private Handler mHandler = null;
	
	public void doQuit(){
		//释放资源
		handlerThread.quit();
	}
	
	private long startTime = 0;
	private void singleZipFile(int number,String path,Handler handler){
		Message msg1 = new Message();
   	 	msg1.what = 11;
   	 	msg1.obj = "正在处理第"+number+"个数据包...";
   	 	handler.sendMessage(msg1);
   	 	startTime = System.currentTimeMillis();
   	 	
		ZipFileReaderAnalysisHelpor zipHelpor = new ZipFileReaderAnalysisHelpor();
        boolean isOK =  zipHelpor.unzip( path, destPath, key_password);
        Log.e("------isOK--->>>", ""+ isOK);
        System.out.println("------解压用时----->>>" + (System.currentTimeMillis() - startTime));
        
        if(isOK){
            gwyinfo mgwyinfo = zipHelpor.doGWYINFOAnalysisEvent(zipHelpor.getReadFilePath(destPath,"gwyinfo"));
            zipHelpor.doDeleteFile(destPath+"gwyinfo.xml");
            if(mgwyinfo == null){
           	 Message msg = new Message();
           	 msg.what = -11;
           	 msg.obj = "数据解析异常，请检查数据包！";
           	 handler.sendMessage(msg);
            }else{
            	startTime = System.currentTimeMillis();
            	
            	if(mgwyinfo.getNodeMap().get(gwyinfo.TYPE).equals(gwyinfo.TABNORMALLAB)){
            		// 普通 单位名册
            		new GwyinfoDaoImpl(context).addGwyinfo(mgwyinfo);
            		
            		B0111_key = mgwyinfo.getNodeMap().get(gwyinfo.B0111);
            	}else{
            		B0111_key = mgwyinfo.getNodeMap().get(gwyinfo.TYPE);
            	}
           	 	
                ArrayList<String> tableLists = mgwyinfo.getFileNames();
                for(String fileName : tableLists){
                    InputStream inputStream = zipHelpor.getReadFilePath(destPath + "Table/", fileName);
                    if("A01".equals(fileName)){
                    	startTime = System.currentTimeMillis();
                        List<A01> listA01 = zipHelpor.doA01AnalysisEvent(inputStream);
//                        System.out.println("-----解析a01---->>>" + (System.currentTimeMillis() - startTime));
//                        startTime = System.currentTimeMillis();
                        new A01DaoImpl(context).addA01(listA01, B0111_key);
//                        System.out.println("-----插入a01---->>>" + (System.currentTimeMillis() - startTime));
//                        Log.e("-解析-" ,"---a01--->" + listA01.size() + ":" + listA01.get(0).getA0000());
                    }else if("A02".equals(fileName)){
                        List<A02> listA02 = zipHelpor.doA02AnalysisEvent(inputStream);
                        new A02DaoImpl(context).addA02(listA02, B0111_key);
//                        Log.e("-解析-" , "---a02--->" + listA02.size());
                    }else if("A05".equals(fileName)){
                        List<A05> listA05 = zipHelpor.doA05AnalysisEvent(inputStream);
                        new A05DaoImpl(context).addA05(listA05, B0111_key);
//                        Log.e("-解析-" , "---a05--->" + listA05.size());
                    }else if("A06".equals(fileName)){
                        List<A06> listA06 = zipHelpor.doA06AnalysisEvent(inputStream);
                        new A06DaoImpl(context).addA06(listA06, B0111_key);
//                        Log.e("-解析-" , "---A06--->" + listA06.size());
                    }else if("A08".equals(fileName)){
                        List<A08> listA08 = zipHelpor.doA08AnalysisEvent(inputStream);
                        new A08DaoImpl(context).addA08(listA08, B0111_key);
//                        Log.e("-解析-" , "---A08--->" + listA08.size());
                    }else if("A14".equals(fileName)){
                        List<A14> listA14 = zipHelpor.doA14AnalysisEvent(inputStream);
                        new A14DaoImpl(context).addA14(listA14, B0111_key);
//                        Log.e("-解析-" , "---A14--->" + listA14.size());
                    }else if("A15".equals(fileName)){
                        List<A15> listA15 = zipHelpor.doA15AnalysisEvent(inputStream);
                        new A15DaoImpl(context).addA15(listA15, B0111_key);
//                        Log.e("-解析-" , "---A15--->" + listA15.size());
                    }else if("A36".equals(fileName)){
                        List<A36> listA36 = zipHelpor.doA36AnalysisEvent(inputStream);
                        new A36DaoImpl(context).addA36(listA36, B0111_key);
//                        Log.e("-解析-" , "---A36--->" + listA36.size());
                    }else if("A57".equals(fileName)){
                        List<A57> listA57 = zipHelpor.doA57AnalysisEvent(inputStream);
                        new A57DaoImpl(context).addA57(listA57, B0111_key);
//                        Log.e("-解析-" , "---A57--->" + listA57.size());
                    }else if("B01".equals(fileName)){
                        List<B01> listB01 = zipHelpor.doB01AnalysisEvent(inputStream);
                        new B01DaoImpl(context).addB01(listB01, B0111_key);
//                        Log.e("-解析-" , "---B01--->" + listB01.size());
                    }else if("M01".equals(fileName)){
//                        List<MCSubEntry> listM01 = zipHelpor.doM01AnalysisEvent(inputStream);
//                        new GwyinfoDaoImpl(context).addM01Gwyinfo(mgwyinfo, listM01);
//                        Log.e("-解析-" , "---M01--->" + listM01.size());
                    }else if("M02".equals(fileName)){
                        List<M02> listM02 = zipHelpor.doM02AnalysisEvent(inputStream);
                        new M02DaoImpl(context).addM02(listM02, B0111_key);
//                        Log.e("-解析-" , "---M02--->" + listM02.size());
                    }else if("M03".equals(fileName)){ 
                        List<M03> listM03 = zipHelpor.doM03AnalysisEvent(inputStream);
                        new M03DaoImpl(context).addM03(listM03, B0111_key);
//                        Log.e("-解析-" , "---M03--->" + listM03.size());
                    }else{ // A99Z1
                        List<A99Z1> listA99Z1 = zipHelpor.doA99Z1AnalysisEvent(inputStream);
                        new A99Z1DaoImpl(context).addA99Z1(listA99Z1, B0111_key);
//                        Log.e("-解析-" , "---A99Z1--->" + (listA99Z1==null?0:listA99Z1.size()));
                    }
                    zipHelpor.doDeleteFile(destPath+"Table/"+fileName+".xml");
                }
            }
        }else{
	       	 Message msg = new Message();
	       	 msg.what = -11;
	       	 msg.obj = "数据解析异常，请检查数据包！";
	       	 handler.sendMessage(msg);
        }
        zipHelpor.doDeleteFile(destPath+"Table/");
        zipHelpor.doDeleteFile(path);
        System.out.println("-----处理完一个zip时间---->>>" + (System.currentTimeMillis() - startTime));
	}
	
	
	
}
