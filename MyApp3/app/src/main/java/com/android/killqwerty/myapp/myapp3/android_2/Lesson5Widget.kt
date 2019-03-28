package com.android.killqwerty.myapp.myapp3.android_2

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.android.killqwerty.myapp.myapp3.R


class Lesson5Widget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {
            //TODO: я наверное пока оставлю виджет тупо с картинкой, позже думаю найду ему применение. пробежался по теме, все понятно, идеи пока нет че туда впихнуть)
            //TODO: StartAndroid 120урок и предыдущие про активити для виджета, пересмотреть чет не рабочий настрой

//            val widgetText = context.getString(R.string.appwidget_text)
//            // Construct the RemoteViews object
//            val views = RemoteViews(context.packageName, R.layout.lesson5_widget)
//            views.setTextViewText(R.id.appwidget_text, widgetText)
//
//            // Instruct the widget manager to update the widget
//            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}