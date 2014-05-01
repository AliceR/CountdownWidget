package io.alicer.countdownwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CountdownWidget extends AppWidgetProvider {
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		/* Time display */
		long eta = 1414729200000L;
		long now = System.currentTimeMillis();

		long durationMillis = eta - now;

		long diffSeconds = durationMillis / 1000;
		long diffMinutes = diffSeconds / 60;
		long diffHours = diffMinutes / 60;
		long diffDays = diffHours / 24;
		long remainDays = diffHours % 24;

		String days = String.valueOf(diffDays);
		String hours = String.valueOf(remainDays + "h");
		
		/* Progress Bar */
		int max = 191;
		int progress = 191 - (int) diffDays;

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			Intent intent = new Intent(context, CountdownWidget.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
					intent, 0);

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.main);

			views.setTextViewText(R.id.days, days);
			views.setTextViewText(R.id.hours, hours);
			
			views.setProgressBar(R.id.progress_bar, max, progress, false);			

			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		
	}
	
}