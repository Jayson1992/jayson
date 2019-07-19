//package com.example.jayson.tools;
//
//import android.os.CountDownTimer;
//import android.widget.TextView;
//
//import com.cyber.utils.ContextUtil;
//
//public class TimeCountDownTool extends CountDownTimer {
//    private TextView remark1TV, remark2TV, secondTV;
//
//    /**
//     * @param millisInFuture    The number of millis in the future from the call
//     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
//     *                          is called.
//     * @param countDownInterval The interval along the way to receive
//     *                          {@link #onTick(long)} callbacks.
//     */
//    public TimeCountDownTool(long millisInFuture, long countDownInterval, TextView remark1TV, TextView remark2TV, TextView secondTV) {
//        super(millisInFuture, countDownInterval);
//        this.remark1TV = remark1TV;
//        this.remark2TV = remark2TV;
//        this.secondTV = secondTV;
//    }
//
//    @Override
//    public void onTick(long millisUntilFinished) {
//        if (millisUntilFinished / 1000 == 1) {
//            remark1TV.setText(ContextUtil.getApplicationContext().getString(R.string.czyqwxqfhcxtx));
//            remark2TV.setText("");
//            secondTV.setText("");
//            return;
//        }
//        int time = Integer.parseInt("" + millisUntilFinished / 1000);
//        int minute = time / 60;
//        int second = time % 60;
//        String min = ContextUtil.getApplicationContext().getString(R.string.minute);
//        String sec = ContextUtil.getApplicationContext().getString(R.string.second);
//        if (minute >= 1) {
//            secondTV.setText(minute + min + second + sec);
//        } else {
//            secondTV.setText(second + sec);
//        }
//    }
//
//    public TextView getRemark1TV() {
//        return remark1TV;
//    }
//
//    public TextView getRemark2TV() {
//        return remark2TV;
//    }
//
//    public TextView getSecondTV() {
//        return secondTV;
//    }
//
//    @Override
//    public void onFinish() {
//
//    }
//}
