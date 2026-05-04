package de.seuhd.worldcup

import kotlinx.serialization.json.Json
import java.io.File
import kotlinx.serialization.decodeFromString
val bets = mutableListOf<Bet>()
fun main() {
    //TODO: Load JSON data
    //TODO: Implement interactive men
    val json = File("src/main/resources/world_cup_2026_full_data.json").readText()
    val data = Json.decodeFromString<WorldCupData>(json)


    while(true) {
        println("""
    |=== FIFA World Cup 2026 ? Betting Console ===
    |1) Show Standings
    |2) Show Matches
    |3) Place Bets
    |4) Show Betting Score
    |5) Exit
    |=================================================
    |Choose an option (1 to 5) :""".trimMargin())


    when (readln()){
        "1" -> showStandings(data.groups)
        "2" ->showMatches(data.groups)
        "3" ->placeBets(data.groups)
        "4" ->showBettingScore(data.groups)
        "5" -> return
        else -> println("invalid input")
    }
} }

/* -------------------------------------------------------------
   1) Show Standings
   ------------------------------------------------------------- */
private fun showStandings(allGroups: List<Group>) {
    //TODO
    println("select a group: ")
    val groupselection = readln()

        for (group in allGroups){
            if (group.name != groupselection &&  groupselection.isNotBlank()){
               continue
            }
            println("=== ${group.name}===")

            val standings = mutableListOf<Triple<String, Int, Int>>()
            for (team in group.teams){
                var points = 0
                var goalsF = 0
                var goalsA = 0

                for (match in group.matches) {
                    if (match.homeScore == null || match.awayScore == null)
                        continue

                    if (team.id == match.homeTeam) {
                        goalsF += match.homeScore
                        goalsA += match.awayScore

                        if (match.homeScore > match.awayScore) points += 3
                        else if (match.homeScore == match.awayScore) points += 1
                    }
                }
                val goaldif = goalsF - goalsA
                standings.add(Triple(team.name, points, goaldif))
                }
            val sort = standings.sortedWith (
                compareByDescending<Triple<String,Int,Int>> {it.second}.thenByDescending { it.third })
            println("team - points - goal difference")
            for (e in sort){
                println("${e.first} ${e.second} ${e.third}")

            }
        }

    }



/* -------------------------------------------------------------
   2) Show Matches
   ------------------------------------------------------------- */
private fun showMatches(allGroups: List<Group>) {
    //TODO
    println("select matches u wanna see")
    val groupselection = readln()
    var found = false

    for (group in allGroups){
        if (group.name.equals(groupselection)){
            found = true
            println("===${group.name}: Matches ===")

            for (match in group.matches){
                val score =
                    if (match.homeScore != null && match.awayScore != null){
                        "${match.homeScore} : ${match.awayScore}"
                    } else {
                        "vs."
                    }
                println("${match.date} - ${match.homeTeam} $score ${match.awayTeam}")
            }
        }
    }
    if (!found){
        println("invalid input")
    }
    readln()
}

/* -------------------------------------------------------------
   3) Place Bets
   ------------------------------------------------------------- */
private fun placeBets(allGroups: List<Group>) {
    //TODO
    println("select group for betting")
    val groupselection = readln()
    var found = false
    for (group in allGroups) {
        if (group.name.equals(groupselection)) {
            found = true
            println("===${group.name}: PlaceBets ===")

            for (match in group.matches) {
                val h = group.teams.find { it.id == match.homeTeam }?.name ?: match.homeTeam
                val a = group.teams.find { it.id == match.awayTeam }?.name ?: match.awayTeam

                println(
                    """
                    |${match.date} - $h vs. $a"
                   |enter tip:
                    |1. home win
                    |2. away win
                    |3. draw
                    """.trimMargin()
                )

                val betinput = readln()
                if(betinput.isBlank()){
                    println("going back to main menu")
                    break
                }
                val prediction = when (betinput) {
                    "1" -> 1
                    "2" -> 2
                    "3" -> 0
                    else -> 0
                }

            bets.add(Bet(match.matchId, prediction))
        }
    }
}
         if (!found) {
          println("invalid input")
      }
        println("changes saved")
    readln()
        }

/* -------------------------------------------------------------
   4) Show Betting Score
   ------------------------------------------------------------- */
private fun showBettingScore(allGroups: List<Group>) {
//TODO
}