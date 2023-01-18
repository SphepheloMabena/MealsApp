package com.example.mealsapp.main.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.mealsapp.main.theme.MealsAppTheme
import com.example.mealsapp.main.viewModels.MealsViewModel

@Composable
fun MainScreen() {
    val viewModel: MealsViewModel = viewModel<MealsViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Black) {
                Text(text = "Meals App", color = Color.White)
            }
        }

    ) {
        LazyColumn(){
            viewModel.mealsCategory.value?.let { meals ->
                items(meals.categories) { category ->
                    MealsCategory(
                        categoryName = category.strCategory ,
                        categoryDescription = category.strCategoryDescription ,
                        imageUrl =category.strCategoryThumb,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OurPreview() {
    MealsAppTheme {
        MainScreen()
    }
}

@Composable
fun MealsCategory(
    categoryName: String,
    categoryDescription: String,
    imageUrl: String
) {
    var descriptionExpandedState:MutableState<Boolean> = remember { mutableStateOf(false) }

    Card(modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 8.dp),
    elevation = 4.dp,
    ) {
        
        Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically) {

            MealPicture(mealPictureId = imageUrl)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(0.85F)
            ) {
                mealCategoryTitle(categoryName = categoryName )
                mealCategoryDescription(categoryDescription = categoryDescription, descriptionExpandedState)

            }
            ShowMoreButton(descriptionExpandedState)
        }
    }
}

@Composable
fun MealPicture(mealPictureId: String){
    AsyncImage(
        model = mealPictureId,
        contentDescription = "Translated description of what the image contains",
        modifier = Modifier.apply {
            size(80.dp, 80.dp)
            padding(3.dp)
            padding(top = 10.dp)
        }
    )
}

@Composable
fun mealCategoryTitle(categoryName: String){
    Text(text = categoryName,
    style = MaterialTheme.typography.h6,
    modifier = Modifier.padding(top = 10.dp)
    )
}

@Composable
fun mealCategoryDescription(categoryDescription: String, showMoreEnabled: MutableState<Boolean>){

    var maxLines = if (showMoreEnabled.value){
        100
    } else {
        4
    }
    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(text = categoryDescription,
            style = MaterialTheme.typography.body1,
            maxLines = maxLines,
            modifier = Modifier.apply {
                padding(top = 8.dp, bottom = 20.dp, start = 4.dp)
            }
        )
    }
}

@Composable fun ShowMoreButton(showMoreEnabled: MutableState<Boolean>) {

    var imageVector = if (showMoreEnabled.value) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
    IconButton(onClick = {
        imageVector = if(showMoreEnabled.value) {
            Icons.Filled.KeyboardArrowDown
        } else {
            Icons.Filled.KeyboardArrowUp
        }
        showMoreEnabled.value = !showMoreEnabled.value
    })
    {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier.apply {
                padding(16.dp)
                size(50.dp,50.dp)
            },
        )
    }
}
