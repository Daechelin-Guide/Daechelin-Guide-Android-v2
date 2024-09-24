package com.dgsw.guidedaechelin.presentation.features.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.dgsw.guidedaechelin.R
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import com.dgsw.guidedaechelin.presentation.features.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class AppWidgetProvider : AppWidgetProvider(){
    @Inject
    lateinit var menuRepository: MenuRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {

        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val dateToday = LocalDate.now().format(formatter)
        val dateTommorow = LocalDate.now().plusDays(1).format(formatter)

        CoroutineScope(Dispatchers.IO).launch {
            val menuToday = async {
                menuRepository.getMenu(date = dateToday.toString())
            }
            val menuTomorrow = async {
                menuRepository.getMenu(date = dateTommorow.toString())
            }
            launch(Dispatchers.Main) {
                updateWidget(context, appWidgetManager, appWidgetIds, menuToday.await(), menuTomorrow.await())
            }
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    private fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        menuToday: MenuModel,
        menuTomorrow: MenuModel
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.app_widget).apply {
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            setOnClickPendingIntent(R.id.widget_frame, pendingIntent)

            val date = LocalDateTime.now()
            val currentTime = date.hour

            if (currentTime < 9) {

                if(menuToday.breakfast == null){
                    setTextViewText(R.id.widget_menu,"급식 정보가 없습니다")
                }else{
                    setTextViewText(R.id.widget_menu, menuToday.breakfast)
                }
                setImageViewResource(
                    R.id.widget_img,
                    R.drawable.orange_widget
                )

            } else if (currentTime < 13) {

                if(menuToday.lunch == null){
                    setTextViewText(R.id.widget_menu,"급식 정보가 없습니다")
                }else{
                    setTextViewText(R.id.widget_menu, menuToday.lunch)
                }
                setImageViewResource(
                    R.id.widget_img,
                    R.drawable.green_widget
                )

            } else if (currentTime < 20){
                if(menuToday.dinner == null){
                    setTextViewText(R.id.widget_menu,"급식 정보가 없습니다")
                }else{
                    setTextViewText(R.id.widget_menu, menuToday.dinner)
                }
                setImageViewResource(
                    R.id.widget_img,
                    R.drawable.violet_widget
                )
            } else {
                if(menuTomorrow.breakfast == null){
                    setTextViewText(R.id.widget_menu,"급식 정보가 없습니다")
                }else{
                    setTextViewText(R.id.widget_menu, menuTomorrow.breakfast)
                }
                setImageViewResource(
                    R.id.widget_img,
                    R.drawable.orange_widget
                )
            }
        }

        appWidgetIds.forEach { appWidgetId ->
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }



}