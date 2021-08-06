package nethical.stif;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.github.angads25.filepicker.controller.DialogSelectionListener ;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.rosemoe.editor.*;
import com.github.angads25.filepicker.*;
import io.github.rosemoe.editor.langs.base.*;
import io.github.rosemoe.editor.langs.java.*;
import com.zip4j.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import java.io.File;
import java.io.InputStream;

public class AllprojectsActivity extends  AppCompatActivity  { 
	
	
	private FloatingActionButton _fab;
	private double n = 0;
	private String logopath = "";
	private String path = "";
	private HashMap<String, Object> mep = new HashMap<>();
	private String logoPath = "";
	private String tempppath = "";
	private HashMap<String, Object> binmap = new HashMap<>();
	
	private ArrayList<String> AllFiles = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<String> temp = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> appdetails = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private TextView textview1;
	private ImageView imageview1;
	private RecyclerView recyclerview1;
	
	private Intent temt = new Intent();
	private AlertDialog.Builder dio;
	private AlertDialog dio3;
	private FilePickerDialog dialog;
	private ProgressDialog pd;
	private ObjectAnimator obnim = new ObjectAnimator();
	private Vibrator vib;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.allprojects);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		recyclerview1 = (RecyclerView) findViewById(R.id.recyclerview1);
		dio = new AlertDialog.Builder(this);
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				path = FileUtil.getExternalStorageDir();
				dio3 = new AlertDialog.Builder(AllprojectsActivity.this).create();
				LayoutInflater dio3LI = getLayoutInflater();
				View dio3CV = (View) dio3LI.inflate(R.layout.create_new_project_dialog, null);
				dio3.setView(dio3CV);
				final ImageView Logo = (ImageView)
				dio3CV.findViewById(R.id.Logo);
				final EditText packagename = (EditText)
				dio3CV.findViewById(R.id.packagename);
				final EditText appname = (EditText)
				dio3CV.findViewById(R.id.appname);
				final Button donebtn = (Button)
				dio3CV.findViewById(R.id.donebtn);
				final TextView info = (TextView)
				dio3CV.findViewById(R.id.info);
				Logo.setOnClickListener(new View.OnClickListener() {
					
					//Set the icon for app
						@Override
						public void onClick(View _view) {
						
						DialogProperties properties = new DialogProperties();
						    properties.selection_mode = DialogConfigs.SINGLE_MODE;
						    properties.selection_type = DialogConfigs.FILE_SELECT;
						    properties.root = new File(path);
						    properties.error_dir = new File(path);
						    properties.offset = new File(path);
						    properties.extensions = null;
						
						    FilePickerDialog dialog = new FilePickerDialog(AllprojectsActivity.this,properties);
						    dialog.setTitle("Select a logo for your app");
						
						dialog.show();
						    dialog.setDialogSelectionListener(new DialogSelectionListener() {
							        @Override
							        public void onSelectedFilePaths(String[] files) {
								
								try {
									Logo.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(files[0], 1024, 1024));
									logoPath = files[0];
								} catch(Exception e) {
									SketchwareUtil.showMessage(getApplicationContext(), "Please Select An Image");
								}
								
								        }
							    });
					}
				});
				//Create A New Project with the entered details
				donebtn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								
						
						if (!appname.getText().toString().equals("")) {
							if (!packagename.getText().toString().equals("")) {
								if (!logoPath.equals("")) {
									pd = new ProgressDialog(AllprojectsActivity.this);
									pd.setTitle("Creating Project");
									pd.show();
									new AsyncTask<String, String, String>() {
											
											@Override
											protected String doInBackground(String... params) {
													String _param = params[0];
													 _CreateFiles("vvg", packagename.getText().toString(), appname.getText().toString());
													return "";
											}
										
										
											@Override
											protected void onPostExecute(String _result) {
													 pd.dismiss();
											_refresh();
											}
										
									}.execute("");
								}
								else {
									_emptyAnimation(Logo);
									info.setText("Select a Logo");
									info.setTextColor(0xFFF44336);
								}
							}
							else {
								_emptyAnimation(packagename);
								info.setText("Enter a Package Name");
								info.setTextColor(0xFFF44336);
							}
						}
						else {
							_emptyAnimation(appname);
							info.setText("Enter a App Name");
							info.setTextColor(0xFFF44336);
						}
					}
				});
				dio3.show();
			}
		});
	}
	
	private void initializeLogic() {
		if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/"))) {
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/"));
		}
		if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/"))) {
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/"));
		}
		_refresh();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _List (final ArrayList<String> _list, final ArrayList<HashMap<String, Object>> _map, final String _key) {
		//This Function converts the list to listmap
		n = 0;
		for(int _repeat11 = 0; _repeat11 < (int)(_list.size()); _repeat11++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put(_key, AllFiles.get((int)(n)));
				map.add(_item);
			}
			
			n++;
		}
	}
	
	
	public void _refresh () {
		map.clear();
		temp.clear();
		FileUtil.listDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/"), AllFiles);
		_List(AllFiles, map, "pj");
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		recyclerview1.setAdapter(new Recyclerview1Adapter(map));
	}
	
	
	public void _CreateFiles (final String _path, final String _packagen, final String _appname) {
		path = String.valueOf((long)(map.size() + 1));
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/".concat(path)));
		tempppath = FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/".concat(path));
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(path)));
		//Copies the template files to the newly create project files
		{
			try{
				int count;
				java.io.InputStream input= AllprojectsActivity.this.getAssets().open("template.zip");
				java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getPackageDataDir(getApplicationContext()) + "/template.zip");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		try{
				ZipFile z = new ZipFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/template.zip"));
				z.extractAll(tempppath);
			 
		}catch(Exception e ){	
			finishAffinity();
		}
		//This code creates the java package from the given package Name by replacing all dots(.) to Slash (/)
		FileUtil.makeDir(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/")));
		//Copies the PreWritten Java classes and activities to the project path
		{
			try{
				int count;
				java.io.InputStream input= AllprojectsActivity.this.getAssets().open("Java.zip");
				java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getPackageDataDir(getApplicationContext()) + "/Java.zip");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		try{
				ZipFile z = new ZipFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/Java.zip"));
				z.extractAll(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/")));
			 
		}catch(Exception e ){	
			finishAffinity();
		}
		//The PreWritten classes contain packagename written as $package name$ . this code replaces them with the new package names
		FileUtil.writeFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/MainActivity.java")), FileUtil.readFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/MainActivity.java"))).replace("$package name$", _packagen));
		FileUtil.writeFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/DebugActivity.java")), FileUtil.readFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/DebugActivity.java"))).replace("$package name$", _packagen));
		FileUtil.writeFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/ErrorHandler.java")), FileUtil.readFile(tempppath.concat("/app/src/main/java/").concat(_packagen.replace(".", "/").concat("/ErrorHandler.java"))).replace("$package name$", _packagen));
		//copies the res folders 
		{
			try{
				int count;
				java.io.InputStream input= AllprojectsActivity.this.getAssets().open("res.zip");
				java.io.OutputStream output = new  java.io.FileOutputStream(FileUtil.getPackageDataDir(getApplicationContext()) + "/res.zip");
				byte data[] = new byte[1024];
				while ((count = input.read(data))>0) {
					output.write(data, 0, count);
				}
				output.flush();
				output.close();
				input.close();
				
				}catch(Exception e){
						
				}
		}
		FileUtil.makeDir(tempppath.concat("/app/src/main/res"));
		try{
				ZipFile z = new ZipFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/res.zip"));
				z.extractAll(tempppath.concat("/app/src/main/res"));
			 
		}catch(Exception e ){	
			finishAffinity();
		}
		FileUtil.makeDir(tempppath.concat("/app/src/main/assets"));
		FileUtil.makeDir(tempppath.concat("/app/libs/"));
		//this code generates the manifest files
		//TO BE IMPROVED
		FileUtil.writeFile(tempppath.concat("/app/src/main/AndroidManifest.xml"), "\n<manifest\n	xmlns:android=\"http://schemas.android.com/apk/res/android\"\n	package=\"".concat(_packagen.concat("\">\n	<application\n		android:allowBackup=\"true\"\n		android:label=\"".concat(_appname.concat("\"\n		android:icon=\"@drawable/app_icon\"\n		android:largeHeap=\"true\"\n		android:usesCleartextTraffic=\"true\"\n		android:name=\".ErrorHandler\"\n		android:theme=\"@style/AppTheme\">\n		<activity\n			android:name=\".MainActivity\"\n			android:configChanges=\"orientation|screenSize|keyboardHidden|smallestScreenSize|screenLayout\"\n			android:hardwareAccelerated=\"true\"\n			android:supportsPictureInPicture=\"true\"\n			android:screenOrientation=\"portrait\">\n			<intent-filter>\n				<action	android:name=\"android.intent.action.MAIN\"/>\n				<category	android:name=\"android.intent.category.LAUNCHER\"/>\n			</intent-filter>\n		</activity>\n		<uses-library\n			android:name=\"org.apache.http.legacy\"\n			android:required=\"false\"/>\n	</application>\n</manifest>\n")))));
		//copies the logo
		FileUtil.copyFile(logoPath, FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(path.concat("/app_icon.png"))));
		FileUtil.copyFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(path.concat("/app_icon.png"))), FileUtil.getPackageDataDir(getApplicationContext()).concat("/projects/".concat(path.concat("/app/src/main/res/drawable-xhdpi/app_icon.png"))));
		//Creates the compiler options for the project
		mep.clear();
		mep = new HashMap<>();
		mep.put("package", _packagen);
		mep.put("name", _appname);
		mep.put("logo", FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(path.concat("/app_icon.png"))));
		mep.put("mainpath", tempppath);
		mep.put("respath", tempppath.concat("/app/src/main/res"));
		mep.put("javapath", tempppath.concat("/app/src/main/java/"));
		mep.put("javapath", tempppath.concat("/app/src/main/java/"));
		mep.put("manifestpath", tempppath.concat("/app/src/main/AndroidManifest.xml"));
		mep.put("assetspath", tempppath.concat("/app/src/main/assets"));
		mep.put("libspath", tempppath.concat("/app/libs/"));
		//finally writes all of them to a json file
		FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(path)).concat("/config"), new Gson().toJson(mep));
		dio3.dismiss();
	}
	
	
	public void _emptyAnimation (final View _view) {
		vib.vibrate((long)(100));
		obnim.setTarget(_view);
		obnim.setPropertyName("translationX");
		obnim.setInterpolator(new BounceInterpolator());
		obnim.setFloatValues((float)(0), (float)(50));
		obnim.setDuration((int)(1000));
		obnim.start();
		obnim.setTarget(_view);
		obnim.setPropertyName("translationX");
		obnim.setInterpolator(new BounceInterpolator());
		obnim.setFloatValues((float)(50), (float)(0));
		obnim.setDuration((int)(1000));
		obnim.start();
	}
	
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		ArrayList<HashMap<String, Object>> _data;
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _v = _inflater.inflate(R.layout.projects, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = (androidx.cardview.widget.CardView) _view.findViewById(R.id.cardview1);
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout extender = (LinearLayout) _view.findViewById(R.id.extender);
			final ImageView circleimageview1 = (ImageView) _view.findViewById(R.id.circleimageview1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout drop_linear = (LinearLayout) _view.findViewById(R.id.drop_linear);
			final TextView filename = (TextView) _view.findViewById(R.id.filename);
			final ImageView dropbtn = (ImageView) _view.findViewById(R.id.dropbtn);
			
			try {
				binmap.clear();
				binmap = new Gson().fromJson(FileUtil.readFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/data/".concat(Uri.parse(_data.get((int)_position).get("pj").toString()).getLastPathSegment().concat("/").concat("config")))), new TypeToken<HashMap<String, Object>>(){}.getType());
				filename.setText(binmap.get("name").toString());
				circleimageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(binmap.get("logo").toString(), 1024, 1024));
			} catch(Exception e) {
				SketchwareUtil.showMessage(getApplicationContext(), e.toString());
			}
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					//writes the project number to cr.project file to help fragments work with it
					//To Be Improved 
					FileUtil.writeFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/cr.project"), Uri.parse(_data.get((int)_position).get("pj").toString()).getLastPathSegment());
					temt.setClass(getApplicationContext(), CodeActivity.class);
					startActivity(temt);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder{
			public ViewHolder(View v){
				super(v);
			}
		}
		
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	

	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	

	
}
