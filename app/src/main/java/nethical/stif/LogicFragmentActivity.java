package nethical.stif;
/*
import com.github.angads25.filepicker.*;
import io.github.rosemoe.editor.interfaces.EditorLanguage;
import io.github.rosemoe.editor.langs.EmptyLanguage;
import io.github.rosemoe.editor.langs.java.JavaLanguage;
import io.github.rosemoe.editor.widget.CodeEditor;
import io.github.rosemoe.editor.widget.EditorColorScheme;
import io.github.rosemoe.editor.widget.schemes.SchemeGitHub;
*/
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import com.tyron.layouteditor.ApplicationLoader;
import com.tyron.layouteditor.DesignActivity;

public class LogicFragmentActivity extends  Fragment  { 
	
	
	private EditText editor;

	private  Intent intent;
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.logic_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		
		editor = (EditText) _view.findViewById(R.id.editor);
	}
	
	private void initializeLogic() {
		
	//there was some compiler issue using rosemoe codeditor
		/*editor.setTypefaceText(Typeface.MONOSPACE);
		
		editor.setOverScrollEnabled(true);
		
		//editor.setEdgeEnabled(false);
		
		editor.setEditorLanguage(new JavaLanguage());
		
		editor.setColorScheme(new SchemeGitHub());
		
		editor.setTextSize(12);
		SketchwareUtil.showMessage(getContext(), FileUtil.readFile(FileUtil.getPackageDataDir(getContext()).concat("/cr.project")));
	    */
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
}
