package com.dgsw.guidedaechelin.presentation.features.widget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.dgsw.guidedaechelin.domain.model.MenuModel
import com.dgsw.guidedaechelin.domain.repository.MenuRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class WidgetAlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var menuRepository: MenuRepository

    val TAG = "WidgetAlarmReceiver"

    override fun onReceive(context: Context, intent: Intent) {

        fun getMenuAndUpdateWidget() {
            CoroutineScope(Dispatchers.IO).launch{
                val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                val dateToday = LocalDate.now().format(formatter)
                val dateTomorrow = LocalDate.now().plusDays(1).format(formatter)

                val menuToday: MenuModel = menuRepository.getMenu(date = dateToday)
                val menuTomorrow: MenuModel = menuRepository.getMenu(date = dateTomorrow)

                updateWidget(menuToday, menuTomorrow, context)
            }
        }

        getMenuAndUpdateWidget()

    }

    private fun updateWidget(menuToday: MenuModel, menuTomorrow: MenuModel, context : Context) {

        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(context, AppWidgetProvider::class.java))

        appWidgetIds.forEach { appWidgetId ->
            AppWidgetProvider().updateWidget(context, appWidgetManager, appWidgetId, menuToday, menuTomorrow)
        }
    }

}
