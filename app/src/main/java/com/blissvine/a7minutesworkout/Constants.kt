package com.blissvine.a7minutesworkout

object Constants {
    fun defaultExerciseList(): ArrayList<ExerciseModel>{
         val exerciseList = ArrayList<ExerciseModel>()
          val fullPlank = ExerciseModel(
               1,
                 "Full Plank",
                 R.drawable.ic_full_plank,
              false,
              false
          )

        exerciseList.add(fullPlank)

        val warriorPose = ExerciseModel(
            2,
            "Warrior Pose",
            R.drawable.ic_warrior_pose,
            false,
            false
        )

        exerciseList.add(warriorPose)



        val catPose = ExerciseModel(
            3,
            "Cat Pose",
            R.drawable.ic_cat_pose,
            false,
            false
        )

        exerciseList.add(catPose)


        val legForwardPose = ExerciseModel(
            4,
            "Leg Forward",
            R.drawable.ic_legforwarrd_pose,
            false,
            false
        )

        exerciseList.add(legForwardPose)

        val caterpillarPose = ExerciseModel(
            5,
            "Caterpillar Pose",
            R.drawable.ic_caterpillar_pose,
            false,
            false
        )

        exerciseList.add(caterpillarPose)


        val triangleForwardPose = ExerciseModel(
            6,
            "Triangle Forward Pose",
            R.drawable.ic_triangle_forward_pose,
            false,
            false
        )

        exerciseList.add(triangleForwardPose)


        val treePose = ExerciseModel(
            7,
            "Tree Pose",
            R.drawable.ic_tree_pose,
            false,
            false
        )

        exerciseList.add(treePose)


        val dogLegUpStackHips = ExerciseModel(
            8,
            "Dog Leg Up Stack Hips",
            R.drawable.downward_dog_leg_up_stack_hups,
            false,
            false
        )

        exerciseList.add(dogLegUpStackHips)



        val camelPose = ExerciseModel(
            9,
            "Camel Pose",
            R.drawable.camel_pose,
            false,
            false
        )

        exerciseList.add(camelPose)


        val plowPose = ExerciseModel(
            10,
            "Plow Pose",
            R.drawable.plow_pose,
            false,
            false
        )

        exerciseList.add(plowPose)

        val standingForwardBendPose = ExerciseModel(
            11,
            "Standing Forward Bend Pose ",
            R.drawable.standing_forward_bend_pose,
            false,
            false
        )

        exerciseList.add(standingForwardBendPose)

        return exerciseList
    }
}