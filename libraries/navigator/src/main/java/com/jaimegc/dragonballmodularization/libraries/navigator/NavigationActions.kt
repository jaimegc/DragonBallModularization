package com.jaimegc.dragonballmodularization.libraries.navigator

import android.content.Context
import android.content.Intent
import com.jaimegc.dragonballmodularization.libraries.navigator.ActionKeys.DRAGON_BALL_ID_KEY

object NavigationActions {

    fun navigateToHomeScreen(context: Context) =
        internalIntent(context, "com.dragonballmodularization.home.navigate").also {
            navigate(context, it)
        }

    fun navigateToAgentDetailsScreen(context: Context, agentId: String) =
        internalIntent(context, "com.dragonballmodularization.dragon_ball_details.navigate")
            .apply { putExtra(DRAGON_BALL_ID_KEY, agentId) }
            .also {
                navigate(context, it)
            }

    private fun internalIntent(context: Context, action: String) =
        Intent(action).setPackage(context.packageName)

    private fun navigate(context: Context, intent: Intent) = context.startActivity(intent)
}
