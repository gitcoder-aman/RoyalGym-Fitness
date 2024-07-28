package com.example.royalgymfitness.backend.util

object Constants {
    const val BASE_URL = "https://exercisedb.p.rapidapi.com/"
    const val API_KEY = "6f692bef33msh5960f4123c6abfdp14ec95jsn768bc1110b47"
    const val API_HOST = "exercisedb.p.rapidapi.com"
}
enum class WorkoutType(){
    TARGET,BODY,EQUIPMENT
}

val targetMap = mapOf(
    "abs" to "https://media.istockphoto.com/id/998035336/photo/muscular-man-standing-in-the-gym.jpg?s=612x612&w=0&k=20&c=J6L6VcDGRs_wVt5B0uEIulZE0NkHNenlNZigUoFVZBU=",
    "abductors" to "https://c8.alamy.com/comp/EFFX6A/hip-abduction-blond-man-exercise-at-gym-indoor-closing-legs-workout-EFFX6A.jpg",
    "biceps" to "https://www.shutterstock.com/image-photo/asian-man-shirtless-workout-weight-600nw-2280545545.jpg",
    "calves" to "https://www.shutterstock.com/image-photo/trained-legs-muscular-calves-sneakers-600nw-2153244895.jpg",
    "cardiovascular system" to "https://www.shutterstock.com/image-photo/group-young-athlete-male-female-600nw-2187804365.jpg",
    "delts" to "https://www.shutterstock.com/image-photo/muscular-man-working-out-gym-600nw-1868894086.jpg",
    "forearms" to "https://www.shutterstock.com/image-photo/strong-athletic-man-fitness-model-600nw-187137251.jpg",
    "glutes" to "https://img.freepik.com/free-photo/calm-young-muscular-caucasian-woman-practicing-gym-with-weights-athletic-female-model-doing-strength-exercises-training-her-lower-body-legs-wellness-healthy-lifestyle-bodybuilding_155003-28053.jpg",
    "hamstrings" to "https://media.istockphoto.com/id/504247240/photo/sporty-legs-hamstrings.jpg?s=612x612&w=0&k=20&c=7r0fzIKgJASrlY42Kn-tfGSVGZi4_1DKWgPIkIRyKWg=",
    "lats" to "https://www.shutterstock.com/image-photo/bodybuilder-gym-muscular-man-working-600nw-2341653227.jpg",
    "levator scapulae" to "https://st.depositphotos.com/46171552/56897/i/450/depositphotos_568973796-stock-photo-back-view-muscular-man-posing.jpg",
    "pectorals" to "https://www.shutterstock.com/image-photo/strong-athletic-man-fitness-model-600nw-587613308.jpg",
    "quads" to "https://media.istockphoto.com/id/1195621671/photo/bodybuilder-working-leg-muscles-in-gym.jpg?s=612x612&w=0&k=20&c=y_ricIqUH6MwK2QGk9YeaAjcB27GVoIt1Ot_lcgwmpQ=",
    "serratus anterior" to "https://barbend.com/wp-content/uploads/2017/11/BarBend-Article-Image-760-x-427-56.jpg",
    "spine" to "https://www.shutterstock.com/image-photo/portrait-young-arab-bodybuilder-suffering-600nw-2114910764.jpg",
    "traps" to "https://athleanx.com/wp-content/uploads/2023/07/TRAPS-THUMB.jpg",
    "triceps" to "https://www.shutterstock.com/image-photo/strong-muscular-handsome-bald-man-600nw-2229623905.jpg",
    "upper back" to "https://www.shutterstock.com/image-photo/man-thrust-upper-block-chest-600nw-2264171221.jpg"
)
val bodyPartMap = mapOf(
    "back" to "https://media.istockphoto.com/id/635971942/photo/back-view-young-adult-girl-doing-barbell-squats.jpg?s=612x612&w=0&k=20&c=7ka8tL-bQ0qSzRw3c0WbAUZ3Ni0mpQdqA4gC0nF14YY=",
    "cardio" to "https://www.shutterstock.com/image-photo/motivated-black-man-making-cardio-600nw-2185610537.jpg",
    "chest" to "https://media.istockphoto.com/id/610554346/photo/gym-workout.jpg?s=612x612&w=0&k=20&c=yV2zqxPnwB2pIIWnHZJuQRLh8cRzgk5gjJPgCbUkOHY=",
    "lower arm" to "https://www.shutterstock.com/image-photo/strong-athletic-man-fitness-model-600nw-187137251.jpg",
    "lower legs" to "https://img.freepik.com/free-photo/low-angle-man-training-gym_23-2149517298.jpg",
    "neck" to "https://thumbs.dreamstime.com/b/charming-blonde-does-exercises-back-gym-neck-pull-head-concept-sports-bodybuilding-fitness-back-view-173872577.jpg",
    "shoulders" to "https://media.istockphoto.com/id/456740017/photo/shoulders-cable-lateral-raise-exercise.jpg?s=612x612&w=0&k=20&c=OWgZeQqx-bpyA2EGNMAfW4UO6eXZRWxx924q-PP9tsE=",
    "upper arms" to "https://www.shutterstock.com/image-photo/sporty-woman-exercising-on-multistation-600nw-2180444683.jpg",
    "upper legs" to "https://media.istockphoto.com/id/1057146526/photo/handsome-man-legs-workout-with-kettlebell-in-the-gym.jpg?s=612x612&w=0&k=20&c=nBQNKfiOa-gYTLNQuWhRNjyIVnMQC_eIizWTqKw2aRU=",
    "waist" to "https://media.istockphoto.com/id/619088796/photo/fitness-girl-lifting-dumbbell-in-the-morning.jpg?s=612x612&w=0&k=20&c=m8gUYPVV1ZfWpfjrKjzWn-a8DVHnw1hGp-rjh6c40f0="
)
val equipmentMap = mapOf(
    "assisted" to "https://media.istockphoto.com/id/2151270602/photo/fitness-instructor-coaching-during-exercise-session.jpg?s=612x612&w=0&k=20&c=LITjiUkS-fvZ249qAheuIFZoWYB17qMrFqu8yd5wSFU=",
    "band" to "https://media.istockphoto.com/id/1145655364/photo/dumbbells-and-rubber-band-for-fitness.jpg?s=612x612&w=0&k=20&c=VGIxga255hEGvG6OcUQNaGifJ37ORY48zTMWo1dZnMk=",
    "barbell" to "https://media.istockphoto.com/id/1391410249/photo/sports-and-gym-activities.jpg?s=612x612&w=0&k=20&c=1S-hAmT-CkRtdYV_hcKi1lZdQkXAN_mCy3ebIXlUEnE=",
    "body weight" to "https://media.istockphoto.com/id/1002863304/photo/increasing-back-shoulder-and-arm-strength.jpg?s=612x612&w=0&k=20&c=RPneGu1SSMq5zNtTB2M5VOAC3SKve6BBjvpET2NHjWs=",
    "bosu ball" to "https://media.istockphoto.com/id/106584886/photo/bosu-balance-trainer-ball.jpg?s=612x612&w=0&k=20&c=mmXTm7bNOMAqdydkywcUb96g33Y_sJyQS9-wYcPAZts=",
    "cable" to "https://media.istockphoto.com/id/1305834342/photo/young-fit-woman-doing-exercise-in-a-crossover-exercise-machine.jpg?s=612x612&w=0&k=20&c=Khx1wvMMh73NMJbdM1RP5oW08PPE7W0N95TJeWUWto0=",
    "dumbbell" to "https://media.istockphoto.com/id/503467544/photo/barbell.jpg?s=612x612&w=0&k=20&c=3djgL5iO3fvxMv2w68S5eBnQXcpq8wqwmFOyhnPThUg=",
    "elliptical machine" to "https://media.istockphoto.com/id/533376859/photo/fitness.jpg?s=612x612&w=0&k=20&c=WZ0dnK4DEty_dIiwk7-FydJQ36DwZXsj63Izjrjz2AA=",
    "ez barbell" to "https://media.istockphoto.com/id/1251724200/vector/curling-bar-barbell-with-black-weight-plates-and-curved-silver-profile.jpg?s=612x612&w=0&k=20&c=cVQAK5EbSEGkGSDmCg0rNaQ3Z3QVF71iwASfp6PqwhQ=",
    "hammer" to "https://media.istockphoto.com/id/1310221797/photo/time-to-let-than-inner-beast-out.jpg?s=612x612&w=0&k=20&c=czYINqfFB2qbZz_ULOiG-_9X75MreLeLdHAFkvxa0ao=",
    "kettlebell" to "https://media.istockphoto.com/id/500834439/photo/sports-kettlebell-medicine-ball-dumbbells.jpg?s=612x612&w=0&k=20&c=yhrV3I60LueNSJBS5yYmeAu6xQERW6ZrnH24YQlRJOA=",
    "leverage machine" to "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRa1hVWuCcwNaSgQXWO2B5WhdO3MIEpn2ve4Q&s",
    "medicine ball" to "https://media.istockphoto.com/id/639897968/photo/attractive-young-women-exercising-with-pilates-ball-at-gym.jpg?s=612x612&w=0&k=20&c=EgW6AQOoWPFGZpjLe0ygdvWoQKj4Ud1x24MrHRC2Prc=",
    "olympic barbell" to "https://media.istockphoto.com/id/1435064109/photo/olympic-weightlifting-snatch.jpg?s=612x612&w=0&k=20&c=bxZ2ab2l6Cp-4tSP2G8BOK4Yv95uFm1mhjcDLhen0W0=",
    "resistance band" to "https://media.istockphoto.com/id/1214303497/photo/sports-accessory-expander-with-a-carbine-on-a-dark-background.jpg?s=612x612&w=0&k=20&c=IgNqIypFz_jZLqnwrtVKQljHTHjlryMy41RydxQ34KY=",
    "roller" to "https://media.istockphoto.com/id/1364209445/photo/fitness-bands-and-rollers-for-yoga-pilates-or-mfr-minimal-home-fitness-concept-top-view-pink.jpg?s=612x612&w=0&k=20&c=gjinZaUQQB918dEH_VC_YYj2bPaqk136SjZSpLPBe8s=",
    "rope" to "https://media.istockphoto.com/id/481079317/photo/jump-rope.jpg?s=612x612&w=0&k=20&c=tSjomdXeGx3vrPhVcNmPQC_5Vmo1JRyBX_-XS-9y1jg=",
    "skierg machine" to "https://media.istockphoto.com/id/937210288/photo/multifunctional-gym-machine-angle-view-isolated-on-white-background-3d-rendering-illustration.jpg?s=612x612&w=0&k=20&c=t0yyOs3VawEklqMuo6QkwGapcPRT6vTBQSzDbWzSS_Q=",
    "sled machine" to "https://media.istockphoto.com/id/466406968/photo/equipment.jpg?s=612x612&w=0&k=20&c=8zYCgANXvaGaWzOn7A7CSzRqcMFPhF1_z77uAknhFEY=",
    "smith machine" to "https://media.istockphoto.com/id/1338185944/photo/a-fit-asian-man-performs-some-squats-at-the-smith-machine-working-out-leg-day-training-at-the.jpg?s=612x612&w=0&k=20&c=WsxJ8ouWlze38ycqAAAaW5_0PMJXkFyI2j6Mmn5bOk0=",
    "stability ball" to "https://media.istockphoto.com/id/465483567/photo/fitness-ball-and-weights-isolated.jpg?s=612x612&w=0&k=20&c=kQUVKhEqKo0lt1o0G_a2j0ipGFtg7adQlCheMgWKO0w=",
    "stationary bike" to "https://media.istockphoto.com/id/465524472/photo/exercise-machines.jpg?s=612x612&w=0&k=20&c=Q2izxWLS_jlVOkc81vcyhIu1yKGaSpukt6uhr5Irzpg=",
    "stepmill machine" to "https://media.istockphoto.com/id/928851972/photo/multifunctional-gym-machine-front-view.jpg?s=612x612&w=0&k=20&c=cIAClatRJ754sEZtbKt6mXlwz3I2hnlsz3nYdc3EjgI=",
    "tire" to "https://media.istockphoto.com/id/1421037205/photo/fit-blonde-woman-flipping-heavy-tire-at-gym.jpg?s=612x612&w=0&k=20&c=L2uTV9Q7yebPy6KijW5Ner42hPxzTkBL8k0fejFfOh0=",
    "trap bar" to "https://t3.ftcdn.net/jpg/05/42/86/72/360_F_542867249_BenpJVcosztPQGx4dvudCttNd5f21YQq.webp",
    "upper body ergometer" to "https://thumbs.dreamstime.com/b/ergometer-upper-body-hand-cycling-arm-shoulder-training-rehabilitation-ergometer-upper-body-hand-cycling-arm-307143240.jpg?w=768",
    "weighted" to "https://media.istockphoto.com/id/1026603090/photo/weighted-squats.jpg?s=612x612&w=0&k=20&c=8hRzSL0Bteu4Wkc0ZoLbM98nYMUnPVjmM5tFqhghsEE=",
    "wheel roller" to "https://media.istockphoto.com/id/919567346/photo/handsome-man-training-with-wheel-to-strengthen-his-abdominal-muscle.jpg?s=612x612&w=0&k=20&c=TVPFjd4Y-T3zksolrhGGvSrfYCplsOrPkTpltBijJYI="
)