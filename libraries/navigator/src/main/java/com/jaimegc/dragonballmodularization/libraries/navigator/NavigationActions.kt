package com.jaimegc.dragonballmodularization.libraries.navigator

import android.content.Context
import android.content.Intent
import com.jaimegc.dragonballmodularization.libraries.navigator.ActionKeys.DRAGONBALL_ID_KEY

object NavigationActions {

    fun navigateToHomeScreen(context: Context, noAnimation: Boolean = true): Intent =
        internalIntent(context, "com.dragonballmodularization.features.home.navigate", noAnimation).also {
            navigate(context, it)
        }

    fun navigateToDragonBallDetailsScreen(context: Context, dragonBallId: Long, noAnimation: Boolean = true): Intent =
        internalIntent(context, "com.dragonballmodularization.features.dragonball_details.navigate", noAnimation)
            .apply { putExtra(DRAGONBALL_ID_KEY, dragonBallId) }
            .also {
                navigate(context, it)
            }

    private fun internalIntent(context: Context, action: String, noAnimation: Boolean) =
        Intent(action).setPackage(context.packageName).apply {
            if (noAnimation) this.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        }

    private fun navigate(context: Context, intent: Intent) = context.startActivity(intent)
}
