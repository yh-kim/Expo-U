package com.expou.tab.t4content;

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
public class ContentAdapter extends ArrayAdapter<ContentItem> {
    //LayoutInflater -> XML을 동적으로 만들 때 필요
    private LayoutInflater inflater = null;
    //Context -> Activity Class의 객체
    private Context contentContext = null;

    public ContentAdapter(Context context, int resource, ArrayList<ContentItem> objects) {
        super(context, resource, objects);

        //context는 함수를 호출한 activiy
        //resource는 row_xxx.xml 의 정보
        this.contentContext = context;
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
            convertView = inflater.inflate(R.layout.row_content,null);

            holder = new ViewHolder();

            //row에 있는 정보들을 holder로 가져옴
            holder.img = (ImageView)convertView.findViewById(R.id.img_content);
            holder.txt_name = (TextView)convertView.findViewById(R.id.txt_name);
            holder.txt_content = (TextView)convertView.findViewById(R.id.txt_content);
            holder.txt_hit = (TextView)convertView.findViewById(R.id.txt_content_hit);
            holder.txt_love = (TextView)convertView.findViewById(R.id.txt_content_love_hit);
            holder.txt_content_title = (TextView)convertView.findViewById(R.id.txt_content_title);

            convertView.setTag(holder);

        }

        holder = (ViewHolder)convertView.getTag();

        ContentItem item = getItem(position);

        holder.img.setImageBitmap(item.getImg());
        holder.txt_name.setText(item.getName());
        holder.txt_content.setText(item.getDetail());
        holder.txt_hit.setText(item.getHit());
        holder.txt_love.setText(item.getLove());
        holder.txt_content_title.setText(item.getExpo()+" / " + item.getBooth());

        return convertView;

    }

    class ViewHolder {
        ImageView img;
        TextView txt_name;
        TextView txt_content;
        TextView txt_hit;
        TextView txt_love;
        TextView txt_content_title;
    }
}
