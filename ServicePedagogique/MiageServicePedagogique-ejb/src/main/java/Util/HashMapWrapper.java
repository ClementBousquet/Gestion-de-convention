/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.HashMap;

/**
 *
 * @author Quentin
 */
public class HashMapWrapper {

    private HashMap<String, String> basketMap;

    public HashMapWrapper(HashMap<String, String> basketMap) {
        this.setBasketMap(basketMap);
    }

    public HashMap<String, String> getBasketMap() {
        return basketMap;
    }

    public void setBasketMap(HashMap<String, String> basketMap) {
        this.basketMap = basketMap;
    }
    
    
    
}
