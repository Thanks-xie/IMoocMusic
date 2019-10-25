package com.xie.com.imoocmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xie.com.imoocmusic.R;

/**
 * 自定义View
 * 1.input_icon: 输入框前面小图标
 * 2.input_hint: 输入框的提示内容
 * 3.is_password: 是否以密文的形式显示
 */
public class InputView extends FrameLayout{

    private int inputIcon;
    private String inputHint;
    private boolean isPassword;

    private View mView;
    private ImageView mInputIcon;
    private EditText mEditText;
    public InputView(@NonNull Context context) {
        super(context);
        init(context,null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attributeSet){
        if (attributeSet==null){
            return;
        }
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.inputView);

        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon,R.mipmap.logo);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);
        typedArray.recycle();

        //绑定Layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view,this,false);
        mInputIcon = mView.findViewById(R.id.iv_icon);
        mEditText = mView.findViewById(R.id.et_edit);

        //布局关联属性
        mInputIcon.setImageResource(inputIcon);
        mEditText.setHint(inputHint);
        mEditText.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

        addView(mView);
    }

    public String getInputString(){
        return mEditText.getText().toString().trim();
    }
}
