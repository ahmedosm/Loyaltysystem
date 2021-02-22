package com.osm.Loyaltysystem.converters;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * code comments 1-performance issue
 * 2-instead of switch cases save fixed data in map
 * 3-added number from setter and
 * constructor not recommended and duplicate code
 * 4-use constructor for mandatory and setters for optional
 * 5- convert method return was missing
 * 6-times method has ambiguity as overloaded method
 * 7-it has wrong calculation  1000 range
 */
public class RomanNumber {

    private int number;
    private static final int maxValue = 3000;
    private final static LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<>();

    public RomanNumber(int number) throws Exception {
        if( number > maxValue ) {
            throw new Exception( "RomanNumber only supports numbers up to 3000" );
        }
        this.number = number;
    }

    static {

        roman_numerals.put( "M", 1000 );
        roman_numerals.put( "CM", 900 );
        roman_numerals.put( "D", 500 );
        roman_numerals.put( "CD", 400 );
        roman_numerals.put( "C", 100 );
        roman_numerals.put( "XC", 90 );
        roman_numerals.put( "L", 50 );
        roman_numerals.put( "XL", 40 );
        roman_numerals.put( "X", 10 );
        roman_numerals.put( "IX", 9 );
        roman_numerals.put( "V", 5 );
        roman_numerals.put( "IV", 4 );
        roman_numerals.put( "I", 1 );
    }

    public String convert() {
        String result = "";
        roman_numerals.entrySet();
        for( Map.Entry<String, Integer> entry : roman_numerals.entrySet() ) {
            int matches = number / entry.getValue();
            result += times( entry.getKey(), matches );
            number = number % entry.getValue();
        }
        return result;
    }

    public String times(String s, int n) {
        final StringBuilder sb = new StringBuilder();
        for( int i = 0; i < n; i++ ) {
            sb.append( s );
        }
        return sb.toString();
    }
}
