//package com.example.jayson.tools;
//
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Point;
//import android.view.Display;
//import android.view.IMainView;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.ImageView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;
//import com.cyber.uwin.R;
//
//import java.util.Objects;
//
//public class ViewTool {
//    public static void setDialogWH(Dialog dialog, Activity activity) {
//        Window dialogWindow = dialog.getWindow();
//        Objects.requireNonNull(dialogWindow).setWindowAnimations(R.style.RightDialogAnimation);
//        WindowManager m = activity.getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        WindowManager.LayoutParams p = Objects.requireNonNull(dialogWindow).getAttributes(); // 获取对话框当前的参数值
//        Point size = new Point();
//        d.getSize(size);
//        p.width = (int) (size.x * 0.8);
//        p.height = (int) (size.y * 0.5);
//        dialogWindow.setAttributes(p);
//    }
//    public static void setDialogW(Dialog dialog, Activity activity) {
//        Window dialogWindow = dialog.getWindow();
//        Objects.requireNonNull(dialogWindow).setWindowAnimations(R.style.RightDialogAnimation);
//        WindowManager m = activity.getWindowManager();
//        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
//        WindowManager.LayoutParams p = Objects.requireNonNull(dialogWindow).getAttributes(); // 获取对话框当前的参数值
//        Point size = new Point();
//        d.getSize(size);
//        p.width = (int) (size.x * 0.8);
//        dialogWindow.setAttributes(p);
//    }
//
//    public static void setMargins(IMainView v, int l, int t, int r, int b) {
//        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
//            p.setMargins(l, t, r, b);
//            v.requestLayout();
//        }
//    }
//
//    public static void setCircleImageView(ImageView imageView, Context context, String url) {
//        RequestOptions myOptions = new RequestOptions()
//                .circleCrop()
//                .placeholder(R.mipmap.default_image)
//                .error(R.mipmap.default_image);
//        Glide.with(context).load(url).apply(myOptions).into(imageView);
//    }
//    public static void setImageView(ImageView imageView, Context context, String url) {
//        RequestOptions myOptions = new RequestOptions()
//                .placeholder(R.mipmap.default_image)
//                .error(R.mipmap.default_image);
//        Glide.with(context).load(url).apply(myOptions).into(imageView);
//    }
//
//    public static void setBarCode(ImageView imageView, String url) {
//        try {
//            Bitmap bitmap = CodeCreator.createQRCode(url, 400, 400, null);
//            imageView.setImageBitmap(bitmap);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//    }
//}
