package com.dgsw.guidedaechelin.presentation.features.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.RemoteViews
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.domain.repository.meal.NewMealRepository
import com.dgsw.guidedaechelin.presentation.features.main.MainActivity
import com.dgsw.guidedaechelin.presentation.features.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import javax.inject.Inject


@AndroidEntryPoint
class AppWidgetProvider : AppWidgetProvider() {
    @Inject
    lateinit var mealRepository: NewMealRepository

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

//        val remoteViews = RemoteViews(context.packageName, com.dgsw.guidedaechelin.R.layout.app_widget)
//
//        // Update the widget with the modified RemoteViews
//
//        val action = intent.action
//
//
//        GlobalScope.launch{
//
//            val localDate = LocalDate.now()
//
//            val data = mealRepository.getMenu(
//                localDate.year.toString(),
//                String.format("%02d", localDate.monthValue),
//                String.format("%02d", localDate.dayOfMonth)
//            )
//
//            when (action) {
//                MORNING_UPDATE_ACTION -> {
//                    remoteViews.setTextViewText(
//                        com.dgsw.guidedaechelin.R.id.widget_menu,
//                        data.breakfast
//
//                    )
//                    remoteViews.setImageViewResource(
//                        com.dgsw.guidedaechelin.R.id.widget_img,
//                        R.drawable.orange_widget
//                    )
//                }
//
//                LUNCH_UPDATE_ACTION -> {
//                    remoteViews.setTextViewText(
//                        com.dgsw.guidedaechelin.R.id.widget_menu,
//                        data.lunch
//
//                    )
//                    remoteViews.setImageViewResource(
//                        com.dgsw.guidedaechelin.R.id.widget_img,
//                        R.drawable.green_widget
//                    )
//                }
//
//                EVENING_UPDATE_ACTION -> {
//                    remoteViews.setTextViewText(
//                        com.dgsw.guidedaechelin.R.id.widget_menu,
//                        data.dinner
//
//                    )
//                    remoteViews.setImageViewResource(
//                        com.dgsw.guidedaechelin.R.id.widget_img,
//                        R.drawable.violet_widget
//                    )
//                }
//            }
//        }
//
//        val appWidgetManager = AppWidgetManager.getInstance(context)
//        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, AppWidgetProvider::class.java))
//        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)

        val remoteViews = RemoteViews(context.packageName, com.dgsw.guidedaechelin.R.layout.app_widget)

        // Set up the alarms when the widget is enabled
        setupAlarms(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.d("최희건","AppWidgetProvider - onUpdate() called")
        Log.d("최희건","appWidgetIds -  ${appWidgetIds.size}")


        for (appWidgetId in appWidgetIds) {
            Log.d("최희건","appWidgetId - ${appWidgetId}")
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, com.dgsw.guidedaechelin.R.layout.app_widget)

        views.setOnClickPendingIntent(R.id.widget_frame, getPendingIntent(context))

        GlobalScope.launch {

            var localDateTime = LocalDateTime.now()

            Log.d("최희건","year - ${ localDateTime.year.toString()}${String.format("%02d", localDateTime.monthValue)}${String.format("%02d", localDateTime.dayOfMonth)}")

            when(localDateTime.hour){

                in 0..9 -> {
                    val data = mealRepository.getMenu(
                        localDateTime.year.toString(),
                        String.format("%02d", localDateTime.monthValue),
                        String.format("%02d", localDateTime.dayOfMonth)
                    )
                    if(data.lunch == null){
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu,"급식 정보가 없습니다")
                    }else{
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu, data.breakfast)
                    }
                    Log.d("최희건","widget 0..9 $data")
                    views.setImageViewResource(
                        com.dgsw.guidedaechelin.R.id.widget_img,
                        R.drawable.orange_widget
                    )
                }

                in 9..14 -> {
                    val data = mealRepository.getMenu(
                        localDateTime.year.toString(),
                        String.format("%02d", localDateTime.monthValue),
                        String.format("%02d", localDateTime.dayOfMonth)
                    )
                    if(data.lunch == null){
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu,"급식 정보가 없습니다")
                    }else{
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu, data.lunch)
                    }
                    Log.d("최희건","widget 9..14 $data")
                    views.setImageViewResource(
                        com.dgsw.guidedaechelin.R.id.widget_img,
                        R.drawable.green_widget
                    )
                }

                in 14..20 -> {
                    val data = mealRepository.getMenu(
                        localDateTime.year.toString(),
                        String.format("%02d", localDateTime.monthValue),
                        String.format("%02d", localDateTime.dayOfMonth)
                    )
                    if(data.dinner == null){
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu,"급식 정보가 없습니다")
                    }else{
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu, data.dinner)
                    }
                    Log.d("최희건","14..20 $data")
                    views.setImageViewResource(
                        com.dgsw.guidedaechelin.R.id.widget_img,
                        R.drawable.violet_widget
                    )
                }

                else -> {
                    val localDateTimePlused = localDateTime.plusDays(1)
                    val data = mealRepository.getMenu(
                        localDateTimePlused.year.toString(),
                        String.format("%02d", localDateTimePlused.monthValue),
                        String.format("%02d", localDateTimePlused.dayOfMonth)
                    )

                    if(data.breakfast == null){
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu,"급식 정보가 없습니다")
                    }else{
                        views.setTextViewText(com.dgsw.guidedaechelin.R.id.widget_menu, data.breakfast)
                    }
                    Log.d("최희건","widget else $data")
                    views.setImageViewResource(
                        com.dgsw.guidedaechelin.R.id.widget_img,
                        R.drawable.orange_widget
                    )
                }

            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val intent = Intent(context, SplashActivity::class.java)
            .setAction(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LAUNCHER)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun setupAlarms(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Create intents to trigger the update of the widget for each time
        val morningIntent = createUpdateIntent(context, MORNING_UPDATE_ACTION)
        val lunchIntent = createUpdateIntent(context, LUNCH_UPDATE_ACTION)
        val eveningIntent = createUpdateIntent(context, EVENING_UPDATE_ACTION)

        // Get the pending intents for each alarm
        val morningPendingIntent = PendingIntent.getBroadcast(context, 0, morningIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val lunchPendingIntent = PendingIntent.getBroadcast(context, 1, lunchIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val eveningPendingIntent = PendingIntent.getBroadcast(context, 2, eveningIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Set the desired update times using Calendar or other means


        val localDate = LocalDate.now()

        val breakfastHour = 10
        val lunchHour = 9
        val dinnerHour = 4

        val breakfastTime = localDate.atTime(breakfastHour,0,0)
        val lunchTime = localDate.atTime(lunchHour,0,0)
        val dinnerTime = localDate.atTime(dinnerHour,0,0)


        val morningUpdateTimeMillis: Long = ZonedDateTime.of(breakfastTime, ZoneId.systemDefault()).toInstant().toEpochMilli()
        val lunchUpdateTimeMillis: Long = ZonedDateTime.of(lunchTime, ZoneId.systemDefault()).toInstant().toEpochMilli()
        val eveningUpdateTimeMillis: Long = ZonedDateTime.of(dinnerTime, ZoneId.systemDefault()).toInstant().toEpochMilli()

        // Set the alarms using AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, morningUpdateTimeMillis, morningPendingIntent)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, lunchUpdateTimeMillis, lunchPendingIntent)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, eveningUpdateTimeMillis, eveningPendingIntent)
    }

    private fun createUpdateIntent(context: Context, action: String): Intent {
        val intent = Intent(context, AppWidgetProvider::class.java)
        intent.action = action
        return intent
    }

    companion object {
        const val MORNING_UPDATE_ACTION = "com.yourapp.action.MORNING_UPDATE"
        const val LUNCH_UPDATE_ACTION = "com.yourapp.action.LUNCH_UPDATE"
        const val EVENING_UPDATE_ACTION = "com.yourapp.action.EVENING_UPDATE"
    }

}
