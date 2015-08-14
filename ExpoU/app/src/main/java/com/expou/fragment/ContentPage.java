package com.expou.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.expou.R;
import com.expou.exception.ServiceException;
import com.expou.item.ContentItem;
import com.expou.serverconnect.dao.ServiceDAOImpl;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class ContentPage extends Fragment {
    View rootView;
    TextView[] txt;
    ImageView img1, img2, img3, img4, img5, img6, img0, img7, dot1, dot2, dot3;
    TextView txt1, txt2, txt3, txt4, txt0;
    CtContent last = new CtContent();
    int position = 0;
    ArrayList<ContentItem> arr_list = new ArrayList<>();

    public static Fragment newInstance() {
        Fragment fragment = new ContentPage();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_contentpage, container, false);

        Bundle arguments = getArguments();

        System.out.println(position + "posi를 받아오느 테스트");

        try {
            arr_list = new ServiceDAOImpl().getContent();
            System.out.println("A");
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        String title = "[삼성] Galaxy S6 edge";
        String subtitle = "5일전 / 조회수 : 14207";
        String content1 = "삼성 갤럭시 S6 엣지(Samsung GALAXY S6 Edge)는 삼성전자\n" +
                "에서 제조, 판매 하는 안드로이드 스마트폰으로, 삼성 갤럭시 \n" +
                "S6 의 파생모델이다. 2015년 3월 1일 스페인 바르셀로나 모바\n" +
                "일 월드 콩그레스에서 열린 삼성 갤럭시 언팩 2015 에서 공개\n" +
                "되어, 4월 10일 출시되었다.";

        String content2 = "크기 :   142.1 x 70.1 x 7mm\n" +
                "색상 : 화이트펄, 블랙사파이어, 골드플래티늄, 그린에메랄드\n" +
                "형태 : 바 (bar)\n" +
                "CPU : 쿼드코어 1.5GHz Cortex-A53 & 쿼드코어 2.1GHz Cortex-A57\n" +
                "RAM : 3GB\n" +
                "운영 체제 : 안드로이드 5.0.2 롤리팝\n" +
                "안드로이드 5.1.1 롤리팝\n" +
                "디스플레이타입 : 슈퍼아몰레드정전식터치스크린\n" +
                "해상도 : 1440 x 2560\n" +
                "배터리용량 : 2600mAh\n" +
                "카메라부가기능 : 광학이미지안정화, 자동초점, LED플래시, \n" +
                "지오태깅, 터치초점, 얼굴인식, 자동HDR, \n" +
                "파노라마, 동영상촬영\n" +
                "후면카메라화소 : 1600만화소\n" +
                "전면카메라화소 : 500만화소";
        String clicked = "11278";


        txt0 = (TextView) rootView.findViewById(R.id.content_title);
        txt0.setText(arr_list.get(position).getName());
        txt1 = (TextView) rootView.findViewById(R.id.content_date);
        txt2 = (TextView) rootView.findViewById(R.id.content1);


        txt3 = (TextView) rootView.findViewById(R.id.content2);
        txt3.setText(content2);


        txt4 = (TextView) rootView.findViewById(R.id.content_number);
        txt4.setText(clicked);


        img2 = (ImageView) rootView.findViewById(R.id.mainimage);
        img3 = (ImageView) rootView.findViewById(R.id.subimage1);


        img4 = (ImageView) rootView.findViewById(R.id.subsubimage1);
        img5 = (ImageView) rootView.findViewById(R.id.subsubimage2);
        img6 = (ImageView) rootView.findViewById(R.id.subsubimage3);


//        img1 = (ImageView)rootView.findViewById(R.id.)
        return rootView;

    }

    public static int setItemNumber(int position) {
        return position;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}