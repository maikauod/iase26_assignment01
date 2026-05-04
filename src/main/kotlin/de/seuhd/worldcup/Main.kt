package de.seuhd.worldcup

import kotlinx.serialization.json.Json
import java.io.File
import kotlinx.serialization.decodeFromString


fun main() {
    //TODO: Load JSON data
    //TODO: Implement interactive menu
    val json = File("world_cup_data.json").readText()
    val data = Json.decodeFromString<WorldCupData>(json)
}

/* -------------------------------------------------------------
   1) Show Standings
   ------------------------------------------------------------- */
private fun showStandings(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   2) Show Matches
   ------------------------------------------------------------- */
private fun showMatches(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   3) Place Bets
   ------------------------------------------------------------- */
private fun placeBets(allGroups: List<Group>) {
    //TODO
}

/* -------------------------------------------------------------
   4) Show Betting Score
   ------------------------------------------------------------- */
private fun showBettingScore(allGroups: List<Group>) {
    //TODO
}