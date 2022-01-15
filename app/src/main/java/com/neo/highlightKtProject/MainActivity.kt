package com.neo.highlightKtProject

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neo.highlight.util.scheme.StyleScheme
import com.neo.highlightKt.extensions.highlight
import com.neo.highlightKtProject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hello.text = "Highlight-KT Project"

        binding.hello.highlight {
            styleScheme(
                style = StyleScheme.STYLE.ITALIC,
                regex = Regex("(Highlight-KT)")
            ) {
                colorScheme(Color.CYAN, Regex("light"))
            }

            colorScheme(Color.RED, Regex("Project"))
        }
    }
}