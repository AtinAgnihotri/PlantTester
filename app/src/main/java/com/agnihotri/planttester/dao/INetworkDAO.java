package com.agnihotri.planttester.dao;

import java.io.IOException;
import java.net.MalformedURLException;

public interface INetworkDAO {

    public String fetch (String keyword) throws IOException;

}
