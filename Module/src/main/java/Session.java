record Session (int id,
                String title,
                String mentor,
                String date,
                String location,
                int maxParts){

}

record SessionList (Session head, SessionList tail){

}