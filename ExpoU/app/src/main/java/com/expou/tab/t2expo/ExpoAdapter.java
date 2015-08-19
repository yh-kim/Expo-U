package com.expou.tab.t2expo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expou.R;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-14.
 */
public class ExpoAdapter extends ArrayAdapter<ExpoItem> {
    //LayoutInflater -> XML을 동적으로 만들 때 필요
    private LayoutInflater inflater = null;
    //Context -> Activity Class의 객체
    private Context expoContext = null;

    public ExpoAdapter(Context context, int resource, ArrayList<ExpoItem> objects) {
        super(context, resource, objects);

        //context는 함수를 호출한 activiy
        //resource는 row_xxx.xml 의 정보
        this.expoContext = context;
        this.inflater = LayoutInflater.from(context);
    }


    //ArrayList에 저장되어있는 데이터를 fragment에 넣는 method
    //List 하나마다 getView가 한번 실행된다
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //position -> List번호
        ViewHolder holder;

        //XML 파일이 비어있는 상태라면
        if(convertView == null){
            //layout 설정
            convertView = inflater.inflate(R.layout.row_expo,null);

            holder = new ViewHolder();

            //row에 있는 정보들을 holder로 가져옴
            holder.img = (ImageView)convertView.findViewById(R.id.img_Expo);
            holder.txtExplain = (TextView)convertView.findViewById(R.id.txt_expo_Explain);
            holder.hit = (TextView)convertView.findViewById(R.id.txt_expo_hit);
            holder.love = (TextView)convertView.findViewById(R.id.txt_expo_love_hit);

            convertView.setTag(holder);
        }

        holder = (ViewHolder)convertView.getTag();

        ExpoItem item = getItem(position);



        holder.img.setImageBitmap(item.getImg());
        holder.txtExplain.setText(item.getName());
        holder.hit.setText(item.getHit());
        holder.love.setText(item.getLove());

        return convertView;

    }

    class ViewHolder {
        ImageView img;
        TextView txtExplain;
        TextView hit;
        TextView love;
    }
}
