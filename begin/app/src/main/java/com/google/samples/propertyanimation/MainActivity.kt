/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.propertyanimation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.addListener


class MainActivity : AppCompatActivity() {

    lateinit var star: ImageView
    lateinit var rotateButton: Button
    lateinit var translateButton: Button
    lateinit var scaleButton: Button
    lateinit var fadeButton: Button
    lateinit var colorizeButton: Button
    lateinit var showerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        star = findViewById(R.id.star)
        rotateButton = findViewById<Button>(R.id.rotateButton)
        translateButton = findViewById<Button>(R.id.translateButton)
        scaleButton = findViewById<Button>(R.id.scaleButton)
        fadeButton = findViewById<Button>(R.id.fadeButton)
        colorizeButton = findViewById<Button>(R.id.colorizeButton)
        showerButton = findViewById<Button>(R.id.showerButton)

        rotateButton.setOnClickListener {
            rotater()
        }

        translateButton.setOnClickListener {
            translater()
        }

        scaleButton.setOnClickListener {
            scaler()
        }

        fadeButton.setOnClickListener {
            fader()
        }

        colorizeButton.setOnClickListener {
            colorizer()
        }

        showerButton.setOnClickListener {
            shower()
        }
    }


    private fun rotater() {

        val animator = ObjectAnimator.ofFloat(star, View.ROTATION, -360f, 0f)
        //set animation duration
        animator.duration = 1000

        //add listener

        animator.disableViewDuringAnimation(rotateButton)

        //start animation
        animator.start()
    }

    private fun translater() {


        val animator = ObjectAnimator.ofFloat(star, View.TRANSLATION_X, 200f)

        animator.apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
        }

        animator.disableViewDuringAnimation(translateButton)
        animator.start()

    }


    private fun scaler() {

        //holds properties to be animated and the start and end values
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)

        /*connect PropertyValuesHolder objects together with
         ObjectAnimator to associate the target object with the
         property and the values info.*/

        val animator = ObjectAnimator.ofPropertyValuesHolder(star, scaleX, scaleY)

        animator.apply {
            duration = 1000

            //make the target return to the beginning position
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE

            //deactivate the button view
            disableViewDuringAnimation(scaleButton)
            start()
        }
    }


    private fun fader() {

        //fade using ALPHA view property
        val animator =
                ObjectAnimator.ofFloat(star, View.ALPHA, 0f)
                        .apply {

                            duration = 1000

                            //reverse the fade
                            repeatCount = 1
                            repeatMode = ObjectAnimator.REVERSE
                            disableViewDuringAnimation(scaleButton)
                            start()
                        }
    }

    private fun colorizer() {

        val animator = ObjectAnimator.ofArgb(star.parent, "backgroundColor", Color.BLACK, Color.RED).apply {
            duration = 1000
            repeatCount = 1
            repeatMode = ObjectAnimator.REVERSE
            disableViewDuringAnimation(colorizeButton)
            start()
        }
    }

    private fun shower() {

        
    }


    fun ObjectAnimator.disableViewDuringAnimation(view: View) {

        addListener(onStart = { view.isEnabled = false }, onEnd = { view.isEnabled = true })


    }

}
