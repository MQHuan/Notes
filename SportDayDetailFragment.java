package com.imco.cocoband.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imco.cocoband.iview.ISportDayDetailView;
import com.imco.cocoband.bean.EachDayDateBean;
import com.imco.cocoband.bean.SleepDataBean;
import com.imco.cocoband.bean.SleepDayDateBean;
import com.imco.cocoband.bean.SportDataBean;
import com.imco.cocoband.bean.SportDayDateBean;
import com.imco.cocoband.presenter.trend.SleepDayPresenter;
import com.imco.cocoband.presenter.trend.SleepMonthPresenter;
import com.imco.cocoband.presenter.trend.SleepWeekPresenter;
import com.imco.cocoband.presenter.trend.SportDayPresenter;
import com.imco.cocoband.presenter.trend.SportMonthPresenter;
import com.imco.cocoband.presenter.trend.SportWeekPresenter;
import com.imco.cocoband.utils.CocoConstants;
import com.imco.common.base.BaseActivity;
import com.imco.common.base.BaseFragment;
import com.imco.common.utils.DateUtils;
import com.imco.watchassistant.R;

import com.yc.pedometer.info.StepOneHourInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mqh on 3/26/16.
 */
public class SportDayDetailFragment extends BaseFragment implements ISportDayDetailView {

    private Activity mActivity;
    private ViewPager testViewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.coco_test_viewpager, container, false);
        mActivity = getActivity();
        setupView(contentView);

        return contentView;
    }

    private void setupView(View contentView) {
        setupToolbarWithToggle(contentView, "Sport Detail", (BaseActivity)mActivity);
        testViewPager = (ViewPager) contentView.findViewById(R.id.test_viewpager);

        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < 4; i ++) {//因为ViewPager有预加载，必须有四个成员以上，不然ground 没法remove掉重用的View
            View inflate = View.inflate(mActivity, R.layout.test_viewpager_item, null);
            views.add(inflate);
        }

        testViewPager.setAdapter(new testAdapter(views,
                new SportDayPresenter(this),
                new SportWeekPresenter(),
                new SportMonthPresenter(),
                new SleepDayPresenter(),
                new SleepWeekPresenter(),
                new SleepMonthPresenter()));
    }

    public class testAdapter extends PagerAdapter {

        private List<View> mDatas;
        private SportDayPresenter mSportDayPresenter;
        private SportWeekPresenter mSportWeekPresenter;
        private SportMonthPresenter mSportMonthPresenter;
        private SleepDayPresenter mSleepDayPresenter;
        private SleepWeekPresenter mSleepWeekPresenter;
        private SleepMonthPresenter mSleepMonthPresenter;

        public testAdapter(List<View> datas,
                           SportDayPresenter sportDayModel,
                           SportWeekPresenter sportWeekPresenter,
                           SportMonthPresenter sportMonthPresenter,
                           SleepDayPresenter sleepDayPresenter,
                           SleepWeekPresenter sleepWeekPresenter,
                           SleepMonthPresenter sleepMonthPresenter){
            this.mDatas = datas;
            mSportDayPresenter = sportDayModel;
            mSportWeekPresenter = sportWeekPresenter;
            mSportMonthPresenter = sportMonthPresenter;
            mSleepDayPresenter = sleepDayPresenter;
            mSleepWeekPresenter= sleepWeekPresenter;
            mSleepMonthPresenter = sleepMonthPresenter;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
          container.removeView(mDatas.get(position% mDatas.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            StepInfo daySportInfo = mSportDayPresenter.getDaySportInfo(position);
//            List<StepOneHourInfo> stepOneHourInfo = mSportDayPresenter.getStepOneHourInfo(position);

            View view = mDatas.get(position % mDatas.size());
            //day
            SportDayDateBean daySportData = mSportDayPresenter.getSportDataOfDay(position);
            if (daySportData != null) {
                List<StepOneHourInfo> oneHourInfos = daySportData.getOneHourInfos();
                TextView hourDate = (TextView) view.findViewById(R.id.hour_data);
                TextView stepCount = (TextView) view.findViewById(R.id.step_count);
                TextView totalDistance = (TextView) view.findViewById(R.id.total_distance);
                TextView totalCalories = (TextView) view.findViewById(R.id.total_calories);
                TextView goalComplete = (TextView) view.findViewById(R.id.goal_complete);
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < oneHourInfos.size(); i++) {
                    sb.append(" Time:"+oneHourInfos.get(i).getTime()+" step:"+oneHourInfos.get(i).getStep());
                }
                String s = sb.toString();
                hourDate.setText("hourDate"+daySportData.getDate()+" "+s);
                stepCount.setText("Step Count : "+daySportData.getStep());
                totalDistance.setText("Distance : "+ daySportData.getDistance());
                totalCalories.setText("Calories : "+ daySportData.getCalories());
                goalComplete.setText("GoalComplete : ");

//                for (StepOneHourInfo hourInfo : oneHourInfos) {
//                    sb.append(" Time:"+hourInfo.getTime()+" step:"+hourInfo.getStep());
//                }
            }else{
                TextView hourDate = (TextView) view.findViewById(R.id.hour_data);
                TextView stepCount = (TextView) view.findViewById(R.id.step_count);
                TextView totalDistance = (TextView) view.findViewById(R.id.total_distance);
                TextView totalCalories = (TextView) view.findViewById(R.id.total_calories);
                TextView goalComplete = (TextView) view.findViewById(R.id.goal_complete);

                hourDate.setText("hourDate");
                stepCount.setText("Step Count : ");
                totalDistance.setText("Distance : ");
                totalCalories.setText("Calories : ");
                goalComplete.setText("GoalComplete : ");
            }

            //week
            SportDataBean sportDataOfWeek = mSportWeekPresenter.getSportDataOfWeek(position);
            if (sportDataOfWeek != null){
                TextView hourDataWeek = (TextView) view.findViewById(R.id.hour_data_week);
                TextView stepCountWeek = (TextView) view.findViewById(R.id.step_count_week);
                TextView totalCaloriesWeek = (TextView) view.findViewById(R.id.total_calories_week);
                TextView totalDistanceWeek = (TextView) view.findViewById(R.id.total_distance_week);
                TextView goalCompleteWeek = (TextView) view.findViewById(R.id.goal_complete_week);
                TextView averageStepCount = (TextView) view.findViewById(R.id.average_step_count);

                List<EachDayDateBean> eachDayStepCount = sportDataOfWeek.getEachDayStepCount();

                StringBuffer sb = new StringBuffer();
                for (EachDayDateBean eddb :
                        eachDayStepCount) {
                    sb.append("Date: "+eddb.getDate()+" TotalCount: "+eddb.getTotalCount());
                }

                hourDataWeek.setText(sb.toString());
                stepCountWeek.setText("TotalStepCount: "+sportDataOfWeek.getTotalStepCount());
                totalCaloriesWeek.setText("TotalCalories: "+sportDataOfWeek.getTotalCalories());
                totalDistanceWeek.setText("TotalDistance: "+sportDataOfWeek.getTotalDistance());
                goalCompleteWeek.setText("CompleteCount: "+sportDataOfWeek.getCompleteCount());
                averageStepCount.setText("AverageStep: "+sportDataOfWeek.getAverageStepCount());
            }

            //month
            SportDataBean sportDataOfMonth = mSportMonthPresenter.getSportDataOfMonth(position);
            if (sportDataOfMonth != null){
                TextView hourDataMonth = (TextView) view.findViewById(R.id.hour_data_month);
                TextView stepCountMonth = (TextView) view.findViewById(R.id.step_count_week_month);
                TextView totalCaloriesMonth = (TextView) view.findViewById(R.id.total_calories_week_month);
                TextView totalDistanceMonth = (TextView) view.findViewById(R.id.total_distance_week_month);
                TextView goalCompleteMonth = (TextView) view.findViewById(R.id.goal_complete_week_month);
                TextView averageStepCountMonth = (TextView) view.findViewById(R.id.average_step_count_month);

                List<EachDayDateBean> eachDayStepCount = sportDataOfMonth.getEachDayStepCount();
                StringBuffer sb = new StringBuffer();
                for (EachDayDateBean eddb :
                        eachDayStepCount) {
                    sb.append("Date: "+eddb.getDate()+" TotalCount: "+eddb.getTotalCount());
                }

                hourDataMonth.setText(sb.toString());
                stepCountMonth.setText("TotalStepCount: "+sportDataOfMonth.getTotalStepCount());
                totalCaloriesMonth.setText("TotalCalories: "+sportDataOfMonth.getTotalCalories());
                totalDistanceMonth.setText("TotalDistance: "+sportDataOfMonth.getTotalDistance());
                goalCompleteMonth.setText("CompleteCount: "+sportDataOfMonth.getCompleteCount());
                averageStepCountMonth.setText("AverageStep: "+sportDataOfMonth.getAverageStepCount());
            }

            //sleep Day
            SleepDayDateBean sleepDataOfDay = mSleepDayPresenter.getSleepDataOfDay(position);
            if (sleepDataOfDay != null) {

                TextView sleepStatusView = (TextView) view.findViewById(R.id.sleep_status_view);
                TextView totalSleepTime = (TextView) view.findViewById(R.id.total_sleep_time);
                TextView sleepQuality = (TextView) view.findViewById(R.id.sleep_quality);
                TextView awakeCount = (TextView) view.findViewById(R.id.awake_count);
                TextView deepSleep = (TextView) view.findViewById(R.id.deep_sleep);
                TextView lightSleep = (TextView) view.findViewById(R.id.light_sleep);

                int[] sleepStatusArray = sleepDataOfDay.getSleepStatusArray();
                int[] durationTimeArray = sleepDataOfDay.getDurationTimeArray();
                int[] timePointArray = sleepDataOfDay.getTimePointArray();

                StringBuffer sb = new StringBuffer();
                sb.append("Date: " + DateUtils.getDateByMillseconds(CocoConstants.yyyyMMdd, sleepDataOfDay.getDate()));
                Log.d("TEXTSLEEPTIME", "Date"  + DateUtils.getDateByMillseconds(CocoConstants.yyyyMMdd, sleepDataOfDay.getDate()));
                for (int i = 0; i < sleepStatusArray.length; i++){
                    sb.append("\nSleepStatus:"+sleepStatusArray[i]+" DurationTime:"+durationTimeArray[i]+" timePoint:"+timePointArray[i]);
                    Log.d("TEXTSLEEPTIME", "\nSleepStatus:"+sleepStatusArray[i]+" DurationTime:"+durationTimeArray[i]+" timePoint:"+timePointArray[i]);
                }
                Log.d("TEXTSLEEPTIME", "sleepStatusView" + sb.toString());
                Log.d("TEXTSLEEPTIME", "totalSleepTime" + sleepDataOfDay.getSleepTime());
                Log.d("TEXTSLEEPTIME", "awakeCount: "+sleepDataOfDay.getAwakeCount());
                Log.d("TEXTSLEEPTIME", "deepSleep:"+sleepDataOfDay.getDeepTime());
                Log.d("TEXTSLEEPTIME", "lightSleep:" + sleepDataOfDay.getLightTime());

                sleepStatusView.setText("sleepStatusView"+sb.toString());
                totalSleepTime.setText("totalSleepTime: "+sleepDataOfDay.getSleepTime());
                awakeCount.setText("awakeCount: "+sleepDataOfDay.getAwakeCount());
                deepSleep.setText("deepSleep:"+sleepDataOfDay.getDeepTime());
                lightSleep.setText("lightSleep:" + sleepDataOfDay.getLightTime());
            }

//sleep week
            SleepDataBean sleepDataOfWeek = mSleepWeekPresenter.getSleepDataOfWeek(position);
            if (sleepDataOfWeek != null) {

                TextView sleepStatusView = (TextView) view.findViewById(R.id.sleep_status_view_week);
                TextView totalSleepTime = (TextView) view.findViewById(R.id.total_sleep_time_week);
                TextView sleepQuality = (TextView) view.findViewById(R.id.sleep_quality_week);
                TextView awakeCount = (TextView) view.findViewById(R.id.awake_count_week);
                TextView deepSleep = (TextView) view.findViewById(R.id.deep_sleep_week);
                TextView lightSleep = (TextView) view.findViewById(R.id.light_sleep_week);

                List<EachDayDateBean> eachDayDateBeans = sleepDataOfWeek.getEachDayDateBeans();
                StringBuffer sb = new StringBuffer();
                for (EachDayDateBean eddb :
                        eachDayDateBeans) {
                    sb.append("\nDate: " +eddb.getDate()+" TotalSleepTime:"+eddb.getTotalCount() );
                }

                sleepStatusView.setText("sleepStatusView"+sb.toString());
                totalSleepTime.setText("totalSleepTime: "+sleepDataOfWeek.getAverageSleepTime());
                awakeCount.setText("awakeCount: "+sleepDataOfWeek.getAverageAwakeTime());
                deepSleep.setText("deepSleep:"+sleepDataOfWeek.getAverageDeepTime());
                lightSleep.setText("lightSleep:" + sleepDataOfWeek.getAverageLightTime());
                sleepQuality.setText("CompletionCount: " + sleepDataOfWeek.getCompleteCount());
            }
            //sleep month
            SleepDataBean sleepDataOfMonth = mSleepMonthPresenter.getSleepDataOfMonth(position);
            if (sleepDataOfMonth != null) {

                TextView sleepStatusView = (TextView) view.findViewById(R.id.sleep_status_view_month);
                TextView totalSleepTime = (TextView) view.findViewById(R.id.total_sleep_time_month);
                TextView sleepQuality = (TextView) view.findViewById(R.id.sleep_quality_month);
                TextView awakeCount = (TextView) view.findViewById(R.id.awake_count_month);
                TextView deepSleep = (TextView) view.findViewById(R.id.deep_sleep_month);
                TextView lightSleep = (TextView) view.findViewById(R.id.light_sleep_month);

                List<EachDayDateBean> eachDayDateBeans = sleepDataOfMonth.getEachDayDateBeans();
                StringBuffer sb = new StringBuffer();
                for (EachDayDateBean eddb :
                        eachDayDateBeans) {
                    sb.append("\nDate: " +eddb.getDate()+" TotalSleepTime:"+eddb.getTotalCount() );
                }

                sleepStatusView.setText("sleepStatusView"+sb.toString());
                totalSleepTime.setText("totalSleepTime: "+sleepDataOfMonth.getAverageSleepTime());
                awakeCount.setText("awakeCount: "+sleepDataOfMonth.getAverageAwakeTime());
                deepSleep.setText("deepSleep:"+sleepDataOfMonth.getAverageDeepTime());
                lightSleep.setText("lightSleep:" + sleepDataOfMonth.getAverageLightTime());
                sleepQuality.setText("CompletionCount: " + sleepDataOfMonth.getCompleteCount());
            }
            container.addView(view);
            return view;


        }
    }

    @Override
    public void setStepDataOf24Hour() {

    }

    @Override
    public void setTotalStepCount() {

    }

    @Override
    public void setGoalCompletion() {

    }

    @Override
    public void setTotalDistance() {

    }

    @Override
    public void setTotalCalories() {

    }

    @Override
    public void setTotalSportTime() {

    }
}
