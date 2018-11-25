package com.example.paul.reggie;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Formats currency inputs using regex
public class CurrencyFormatInputFilter implements InputFilter {

    Pattern mPattern = Pattern.compile("(0[1-9]+[0-9]*)(\\.[0-9]{1,2})?");

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){

        String result = dest.subSequence(0,dstart) + source.toString() + dest.subSequence(dend, dest.length());

        Matcher matcher = mPattern.matcher(result);

        if(!matcher.matches()){
            return dest.subSequence(dstart,dend);
        }
        return null;
    }
}
