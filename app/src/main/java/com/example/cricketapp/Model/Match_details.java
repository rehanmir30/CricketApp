package com.example.cricketapp.Model;

public class Match_details {
    String m_id,m_date,m_team1,m_team2,m_toss_winner,m_winner_team,m_squad;

    public Match_details(String m_id, String m_date, String m_team1, String m_team2, String m_toss_winner, String m_winner_team, String m_squad) {
        this.m_id = m_id;
        this.m_date = m_date;
        this.m_team1 = m_team1;
        this.m_team2 = m_team2;
        this.m_toss_winner = m_toss_winner;
        this.m_winner_team = m_winner_team;
        this.m_squad = m_squad;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public String getM_team1() {
        return m_team1;
    }

    public void setM_team1(String m_team1) {
        this.m_team1 = m_team1;
    }

    public String getM_team2() {
        return m_team2;
    }

    public void setM_team2(String m_team2) {
        this.m_team2 = m_team2;
    }

    public String getM_toss_winner() {
        return m_toss_winner;
    }

    public void setM_toss_winner(String m_toss_winner) {
        this.m_toss_winner = m_toss_winner;
    }

    public String getM_winner_team() {
        return m_winner_team;
    }

    public void setM_winner_team(String m_winner_team) {
        this.m_winner_team = m_winner_team;
    }

    public String getM_squad() {
        return m_squad;
    }

    public void setM_squad(String m_squad) {
        this.m_squad = m_squad;
    }
}
