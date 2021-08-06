package nethical.stif;

import io.github.rosemoe.editor.*;
import com.github.angads25.filepicker.*;
import io.github.rosemoe.editor.langs.base.*;
import io.github.rosemoe.editor.langs.java.*;
import com.zip4j.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.view.View;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class FileFragmentActivity extends  Fragment  { 
	
	
	private double n = 0;
	private HashMap<String, Object> prdata = new HashMap<>();
	private String prnum = "";
	private double crpos = 0;
	private String crntfoldee = "";
	
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<String> AllFiles = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private ListView listview1;
	private HorizontalScrollView hscroll1;
	private TextView textview2;
	private LinearLayout linear5;
	private TextView textview4;
	private ImageView imageview1;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.file_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
		linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
		linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
		listview1 = (ListView) _view.findViewById(R.id.listview1);
		hscroll1 = (HorizontalScrollView) _view.findViewById(R.id.hscroll1);
		textview2 = (TextView) _view.findViewById(R.id.textview2);
		linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
		textview4 = (TextView) _view.findViewById(R.id.textview4);
		imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
		
		linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				//Now Works
				if (!crntfoldee.equals(FileUtil.getPackageDataDir(getContext()))) {
					FileUtil.listDir(crntfoldee.substring((int)(0), (int)(crntfoldee.lastIndexOf("/"))), AllFiles);
					crntfoldee = crntfoldee.substring((int)(0), (int)(crntfoldee.lastIndexOf("/")));
					_List(AllFiles, map, "files");
					listview1.setAdapter(new Listview1Adapter(map));
				}
				else {
					SketchwareUtil.showMessage(getContext(), "end");
				}
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (FileUtil.isDirectory(map.get((int)_position).get("files").toString())) {
					FileUtil.listDir(map.get((int)_position).get("files").toString(), AllFiles);
					crntfoldee = map.get((int)_position).get("files").toString();
					_List(AllFiles, map, "files");
					listview1.setAdapter(new Listview1Adapter(map));
				}
			}
		});
	}
	
	private void initializeLogic() {
		if (FileUtil.isExistFile(FileUtil.getPackageDataDir(getContext()).concat("/cr.project"))) {
			prnum = FileUtil.readFile(FileUtil.getPackageDataDir(getContext()).concat("/cr.project"));
		}
		else {
			
		}
		FileUtil.listDir(FileUtil.getPackageDataDir(getContext()).concat("/projects/").concat(prnum.concat("/")), AllFiles);
		_List(AllFiles, map, "files");
		listview1.setAdapter(new Listview1Adapter(map));
		crntfoldee = FileUtil.getPackageDataDir(getContext()).concat("/projects/").concat(prnum.concat("/"));
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _List (final ArrayList<String> _list, final ArrayList<HashMap<String, Object>> _map, final String _key) {
		map.clear();
		n = 0;
		for(int _repeat11 = 0; _repeat11 < (int)(_list.size()); _repeat11++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put(_key, AllFiles.get((int)(n)));
				map.add(_item);
			}
			
			n++;
		}
		SketchwareUtil.sortListMap(map, _key, false, true);
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.files, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final LinearLayout extender = (LinearLayout) _view.findViewById(R.id.extender);
			final LinearLayout drop_linear = (LinearLayout) _view.findViewById(R.id.drop_linear);
			final TextView filename = (TextView) _view.findViewById(R.id.filename);
			final LinearLayout options = (LinearLayout) _view.findViewById(R.id.options);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final ImageView optionsbtn = (ImageView) _view.findViewById(R.id.optionsbtn);
			
			filename.setText(Uri.parse(map.get((int)_position).get("files").toString()).getLastPathSegment());
			if (FileUtil.isFile(map.get((int)_position).get("files").toString())) {
				imageview1.setImageResource(R.drawable.file);
			}
			else {
				imageview1.setImageResource(R.drawable.folder);
			}
			
			return _view;
		}
	}
	
	
}
