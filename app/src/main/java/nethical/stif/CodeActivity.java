package nethical.stif;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
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
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.tyron.layouteditor.ApplicationLoader;
import com.tyron.layouteditor.DesignActivity;

import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class CodeActivity extends  AppCompatActivity  { 
	
	
	private LinearLayout linear1;
	private TabLayout tablayout1;
	private ViewPager viewpager1;
	private ImageView imageview1;
	private TextView prname;
	private LinearLayout linear2;
	private ImageView imageview3;
	private ImageButton ledit;


	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.code);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		tablayout1 = (TabLayout) findViewById(R.id.tablayout1);
		viewpager1 = (ViewPager) findViewById(R.id.viewpager1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		prname = (TextView) findViewById(R.id.prname);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		ledit = (ImageButton) findViewById(R.id.ledit);

		viewpager1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
	}
	
	private void initializeLogic() {
		viewpager1.setAdapter(new MyFragmentAdapter(getApplicationContext(), getSupportFragmentManager(), 2));
		tablayout1.setupWithViewPager(viewpager1);

		ledit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Intent intent = null;
				try {
					intent = new Intent(CodeActivity.this,Class.forName("com.tyron.layouteditor.DesignActivity"));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				startActivity(intent);
			}
		});

	}

	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public class MyFragmentAdapter extends FragmentStatePagerAdapter {
		Context context;
		int tabCount;
		
		public MyFragmentAdapter(Context context, FragmentManager fm, int tabCount) {
			super(fm);
			this.context = context;
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount(){
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			if (_position == 0) {
					return "Logic";
			}
			if (_position == 1) {
					return "files";
			}
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
					return new LogicFragmentActivity();
			}
			if (_position == 1) {
					return new FileFragmentActivity();
			}
			return null;
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
