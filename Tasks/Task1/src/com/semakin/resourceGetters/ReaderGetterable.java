package com.semakin.resourceGetters;

import com.semakin.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by Chi on 09.02.2017.
 */
public interface ReaderGetterable {
    BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException;
    Reader getResourceReader(String resourceAddress) throws InnerResourceException;
}
