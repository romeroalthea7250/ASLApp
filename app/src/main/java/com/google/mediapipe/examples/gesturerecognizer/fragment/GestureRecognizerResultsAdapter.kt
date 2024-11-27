/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.mediapipe.examples.gesturerecognizer.fragment

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.mediapipe.examples.gesturerecognizer.MainActivity
import com.google.mediapipe.examples.gesturerecognizer.R
import com.google.mediapipe.examples.gesturerecognizer.databinding.ItemGestureRecognizerResultBinding
import com.google.mediapipe.tasks.components.containers.Category
import java.util.Locale
import kotlin.math.min

private val textToSpeech: TextToSpeech? = null

class GestureRecognizerResultsAdapter :
    RecyclerView.Adapter<GestureRecognizerResultsAdapter.ViewHolder>() {
    companion object {
        private const val NO_VALUE = "--"
    }

    private var adapterCategories: MutableList<Category?> = mutableListOf()
    private var adapterSize: Int = 0
    private var lastLabel: String? = null
    private var lastLabelSetTime: Long = 0L
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("NotifyDataSetChanged")
    fun updateResults(categories: List<Category>?) {
        adapterCategories = MutableList(adapterSize) { null }
        if (categories != null) {
            val sortedCategories = categories.sortedByDescending { it.score() }
            val min = min(sortedCategories.size, adapterCategories.size)
            for (i in 0 until min) {
                adapterCategories[i] = sortedCategories[i]
            }
            adapterCategories.sortedBy { it?.index() }
            notifyDataSetChanged()
        }
    }

    fun updateAdapterSize(size: Int) {
        adapterSize = size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemGestureRecognizerResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        adapterCategories[position].let { category ->
            holder.bind(category?.categoryName(), category?.score())
        }
    }

    override fun getItemCount(): Int = adapterCategories.size

    inner class ViewHolder(private val binding: ItemGestureRecognizerResultBinding) :
        RecyclerView.ViewHolder(binding.root), TextToSpeech.OnInitListener {

        private var lastLabelSetTime: Long = 0
        private var isLabelShownForOneSecond = false

        private var tts: TextToSpeech? = null
        private var isSpoken = false
        private var lastSpokenText: String? = null

        init {
            tts = TextToSpeech(binding.root.context, this)
        }

        override fun onInit(status: Int) {
            if (status == TextToSpeech.SUCCESS) {
                // Set language here if needed
            }
        }

        fun bind(label: String?, score: Float?) {
            with(binding) {
                tvLabel.text = label ?: ""
                tvScore.text = if (score != null) String.format(Locale.US, "%.2f", score) else NO_VALUE

                // Check if the label has been the same for 2 seconds
                handler.postDelayed({
                    if (tvLabel.text == label && (System.currentTimeMillis() - lastLabelSetTime) >= 1000) {
                        // Do something after 1 second if the label is the same
                        // For example, change text color to indicate 1-second stability
                        tvLabel.setTextColor(tvLabel.context.getColor(android.R.color.holo_green_dark))

                        var sentence = (tvLabel.context as? MainActivity)?.findViewById<TextView>(R.id.resultString)?.text

                        handler.postDelayed({
                            if (tvLabel.text.count() == 1) {
                                sentence = sentence.toString() + tvLabel.text
                            } else {
                                sentence = sentence.toString() + " " + tvLabel.text + " "
                            }

                            // Update the resultString TextView in activity_main.xml
                            (tvLabel.context as? MainActivity)?.findViewById<TextView>(R.id.resultString)?.text = sentence

                            // Speak the label text
//                            tts?.speak(tvLabel.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
                            tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                                override fun onStart(utteranceId: String?) {
                                    // Not used
                                }

                                override fun onDone(utteranceId: String?) {
                                    // Called when the TTS engine finishes speaking
                                    isSpoken = false // Reset the flag
                                }

                                override fun onError(utteranceId: String?) {
                                    // Handle TTS error
                                    isSpoken = false // Reset the flag
                                }
                            })

                            if (lastSpokenText != tvLabel.text.toString()) {
                                tts?.speak(tvLabel.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
                                isSpoken = true // Mark the text as spoken
                                lastSpokenText = tvLabel.text.toString()
                            }
                        }, 1000)
                    } else {
                        tvLabel.setTextColor(tvLabel.context.getColor(android.R.color.black))
                    }
                }, 1000)
            }
        }
    }
}