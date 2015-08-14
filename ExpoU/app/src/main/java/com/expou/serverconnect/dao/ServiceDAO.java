package com.expou.serverconnect.dao;


import com.expou.exception.ServiceException;
import com.expou.item.ContentItem;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-19.
 */
public interface ServiceDAO {
    /** 콘텐츠를 받아오는 기능을 수행 */
    public ArrayList<ContentItem> getContent() throws ServiceException;


}
