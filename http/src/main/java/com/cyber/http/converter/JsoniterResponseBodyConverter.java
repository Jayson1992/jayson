//package com.cyber.http.converter;
//
//import com.jsoniter.JsonIterator;
//
//import java.io.IOException;
//import java.lang.reflect.Type;
//
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//@Deprecated
//@SuppressWarnings("unchecked")
//final class JsoniterResponseBodyConverter<T> implements Converter<ResponseBody, T> {
//
//
//    private Type mType;
//
//
//    JsoniterResponseBodyConverter(Type type) {
//        mType = type;
//
//    }
//
//    @Override
//    public T convert(ResponseBody value) throws IOException {
//        try {
//            return (T) JsonIterator.parse(value.string()).read(mType);
//        } finally {
//            value.close();
//        }
//    }
//}
