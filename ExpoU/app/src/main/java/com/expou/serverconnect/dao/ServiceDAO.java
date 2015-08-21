package com.expou.serverconnect.dao;


import com.expou.exception.ServiceException;
import com.expou.tab.t2expo.ExpoItem;
import com.expou.tab.t3booth.BoothItem;
import com.expou.tab.t4content.ContentItem;
import com.expou.tab.t5market.MarketItem;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-19.
 */
public interface ServiceDAO {
    /** 콘텐츠를 받아오는 기능을 수행 */
    public ArrayList<ContentItem> getContent(int offset) throws ServiceException;

    /** 부스 컨텐츠를 받아오는 기능을 수행 */
    public ArrayList<BoothItem> getBooth(int offset) throws ServiceException;

    /** 엑스포 컨텐츠를 받아오는 기능을 수행 */
    public ArrayList<ExpoItem> getExpo() throws ServiceException;

    /** 마켓 컨텐츠를 받아오는 기능을 수행 */
    public ArrayList<MarketItem> getMarket() throws ServiceException;

    /** 세부 엑스포 받아오는 기능을 수행 */
    public void getDetailExpo(String objectId) throws ServiceException;

    /** 세부 부스 받아오는 기능을 수행 */
    public void getDetailBooth(String objectId) throws ServiceException;

    /** 세부 컨텐츠 받아오는 기능을 수행 */
    public void getDetailContent(String objectId) throws ServiceException;
}
