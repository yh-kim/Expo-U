package com.expou.tab.t5market;

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
public class MarketAdapter extends ArrayAdapter<MarketItem> {
    //LayoutInflater -> XML을 동적으로 만들 때 필요
    private LayoutInflater inflater = null;
    //Context -> Activity Class의 객체
    private Context marketContext = null;

    public MarketAdapter(Context context, int resource, ArrayList<MarketItem> objects) {
        super(context, resource, objects);

        //context는 함수를 호출한 activiy
        //resource는 row_xxx.xml 의 정보
        this.marketContext = context;
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
            convertView = inflater.inflate(R.layout.row_market,null);

            holder = new ViewHolder();

            //row에 있는 정보들을 holder로 가져옴
//            holder.img = (ImageView)convertView.findViewById(R.id.img_Booth);
//            holder.txtTitle = (TextView)convertView.findViewById(R.id.txt_booth_title);
//            holder.txtExplain = (TextView)convertView.findViewById(R.id.txt_booth_explain);

            convertView.setTag(holder);
        }

        holder = (ViewHolder)convertView.getTag();

        MarketItem item = getItem(position);



//        holder.img.setImageResource(R.drawable.booth_img);
//        holder.txtTitle.setText(item.getTxtTitle());
//        holder.img.setImageResource(item.imgExpo);
//        holder.txtExplain.setText(item.getTxtExplain());


        return convertView;

    }

    class ViewHolder {
        ImageView img;
        TextView txtTitle;
        TextView txtExplain;
    }
}
