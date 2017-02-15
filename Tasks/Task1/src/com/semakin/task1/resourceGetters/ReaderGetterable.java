package com.semakin.task1.resourceGetters;

import com.semakin.task1.exceptions.InnerResourceException;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Получатель Reader-а ресурса
 * @author Виктор Семакин
 */
public interface ReaderGetterable {
    /**
     * Получает BufferedReader ресурса по его адресу
     * @param resourceAddress адрес ресурса
     * @return BufferedReader считыватель ресурса
     * @throws InnerResourceException при невозможности получения доступа к ресурсу
     */
    BufferedReader getBufferedReader(String resourceAddress) throws InnerResourceException;

    /**
     * Получает Reader ресурса по его адресу
     * @param resourceAddress
     * @return Reader считыватель ресурса
     * @throws InnerResourceException при невозможности получения доступа к ресурсу
     */
    Reader getResourceReader(String resourceAddress) throws InnerResourceException;
}
