package com.web.Fremdsprache.util;

import java.util.HashSet;
import java.util.Set;

public class ExtraPackage {

	
	public static <T> HashSet<T> mergeSet(Set<T> a, Set<T> b) 
    { 
        return new HashSet<T>() {{ 
                      addAll(a); 
                       addAll(b); 
        } }; 
    } 
	
}
