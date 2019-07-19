//package com.cyber.http.converter;
//
///**
// * Copyright (C) 2017 - gmetal <gmetaxas@gmail.com>
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//import com.jsoniter.JsonIterator;
//import com.jsoniter.spi.DecodingMode;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Type;
//
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import retrofit2.Converter;
//import retrofit2.Retrofit;
//@Deprecated
//public class JsoniterConverterFactory extends Converter.Factory {
//
//    public static JsoniterConverterFactory create() {
//
//        return new JsoniterConverterFactory(DecodingMode.REFLECTION_MODE);
//    }
//
//    public static JsoniterConverterFactory create(final DecodingMode decodingMode) {
//
//        return new JsoniterConverterFactory(decodingMode);
//    }
//
//    private JsoniterConverterFactory(final DecodingMode decodingMode) {
//
//        JsonIterator.setMode(decodingMode);
//    }
//
//    @Override
//    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
//                                                            Retrofit retrofit) {
//
//        return new JsoniterResponseBodyConverter<>(type);
//    }
//
//    @Override
//    public Converter<?, RequestBody> requestBodyConverter(Type type,
//                                                          Annotation[] parameterAnnotations,
//                                                          Annotation[] methodAnnotations,
//                                                          Retrofit retrofit) {
//
//        return new JsoniterRequestBodyConverter<>();
//    }
//}
