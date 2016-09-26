package com.bc.bao.mynumber;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lenovo on 2016/9/26.
 */
public class No extends LinearLayout implements View.OnClickListener{
    private LayoutInflater mInflater;
    private Button btAdd;
    private Button btSub;
    private TextView tvNum;
    private int values;
    private int minValues;
    private int maxValues;
    private BtOnClickListener mbtClick;

    public void setBtOnClickListener(BtOnClickListener listener){
        this.mbtClick = listener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_add){//+
            numAdd();
            if (mbtClick != null){
                mbtClick.btOnClickAddListener(v,values);
            }
        }else if (v.getId() == R.id.bt_sub){//-
            numSub();
            if (mbtClick != null){
                mbtClick.btOnClickSubListener(v,values);
            }
        }
    }
    private void numAdd(){
        if (values < maxValues){
            values++;
        }
        tvNum.setText(values+"");
    }
    private void numSub(){
        if (values > minValues){
            values--;
        }
        tvNum.setText(values+"");
    }

    public interface BtOnClickListener{
        void btOnClickAddListener(View view,int value);
        void btOnClickSubListener(View view,int value);
    }

    public int getValues() {
        String val = tvNum.getText().toString();
        if (val !=null && "".equals(val)){
            this.values = Integer.parseInt(val);
        }else {}
        return values;
    }

    public void setValues(int values) {
        tvNum.setText(values+"");
        this.values = values;
    }

    public int getMinValues() {
        return minValues;
    }

    public void setMinValues(int minValues) {
        this.minValues = minValues;
    }

    public int getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(int maxValues) {
        this.maxValues = maxValues;
    }

    public No(Context context) {
        this(context,null);
    }

    public No(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public No(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initView();
        if (attrs != null){
            TintTypedArray tta = TintTypedArray.obtainStyledAttributes(context,
                    attrs,R.styleable.No,defStyleAttr,0);
            int val = tta.getInt(R.styleable.No_value,0);
            setValues(val);
            int minVal = tta.getInt(R.styleable.No_minValue,0);
            setMinValues(minVal);
            int maxVal = tta.getInt(R.styleable.No_maxValue,0);
            setMaxValues(maxVal);
            Drawable addBt = tta.getDrawable(R.styleable.No_addBtBg);
            setAddBtBg(addBt);
            Drawable subBt = tta.getDrawable(R.styleable.No_subBtBg);
            setAddBtBg(subBt);
            int numTv = tta.getResourceId(R.styleable.No_numTvBg,android.R.color.transparent);
            setNumTvBg(numTv);
            tta.recycle();//回收
        }
    }
    public void setAddBtBg(Drawable drawable) {
        this.btAdd.setBackground(drawable);
    }
    public void setSubBtBg(Drawable drawable) {
        this.btSub.setBackground(drawable);
    }
    private void setNumTvBg(int numTv) {
        this.tvNum.setBackgroundResource(numTv);
    }

    private void initView(){
        View v = mInflater.inflate(R.layout.number_add_del,this,true);
        btAdd = (Button) v.findViewById(R.id.bt_add);
        btSub = (Button) v.findViewById(R.id.bt_sub);
        tvNum = (TextView) v.findViewById(R.id.tv_num);
        btAdd.setOnClickListener(this);
        btSub.setOnClickListener(this);
    }

}
