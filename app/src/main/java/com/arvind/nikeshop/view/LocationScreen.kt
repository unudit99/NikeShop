package com.arvind.nikeshop.view


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.nikeshop.R
import com.arvind.nikeshop.component.TopBarWithLocationIcon
import com.arvind.nikeshop.ui.theme.*
import com.arvind.nikeshop.view.slider.FormViewModel
import com.arvind.nikeshop.view.slider.NaturalModel
import java.util.*
import kotlin.collections.ArrayList

@Preview(showBackground = true)
@Composable
fun LocationScreen(){
    Scaffold(
        topBar = {
            TopBarWithLocationIcon(
            onBackClick = {
                Log.d("Hell","Click detail")

            },
        )
    }, backgroundColor = lightgraybg,
         content = {
             Box(
                 modifier = Modifier
                     .fillMaxSize()
                     .verticalScroll(rememberScrollState())
             ){
                 Column(
                     modifier = Modifier
                         .fillMaxSize()
                         .padding(30.dp)
                 ) {
                     ElectronicCategory()
                     Spacer(modifier = Modifier.padding(10.dp))
                     Text(text = "Urgent Selling",
                         fontWeight = FontWeight.Bold,
                         fontSize = 20.sp,
                         color = red
                     )
                     Spacer(modifier = Modifier.padding(10.dp))
                     AllCategory()
                     Spacer(modifier = Modifier.padding(10.dp))
                     Text(text = "All Products",
                         fontWeight = FontWeight.Bold,
                         fontSize = 20.sp,
                         color = titleTextColor
                     )
                     Spacer(modifier = Modifier.padding(10.dp))
                     Column(
                         modifier=Modifier.padding(start =100.dp, end =50.dp)
                     ) {
                         val imageModifier = Modifier
                             .size(250.dp)
                             .clip(RoundedCornerShape(16.dp))
                         Image(
                             painter = painterResource(id = R.drawable.small_tilt_shoe_1),
                             contentDescription = stringResource(id = R.string.product_text_description),
                             contentScale = ContentScale.Fit,
                             colorFilter = ColorFilter.lighting(Color.LightGray,Color.Blue),
                             modifier = imageModifier
                         )
                         DotsIndicator(5,2)

                     }
                     Spacer(modifier = Modifier.padding(10.dp))
                     Text(text = "List View ",
                         fontWeight = FontWeight.Bold,
                         fontSize = 20.sp,
                         color = titleTextColor
                     )
                     //horizontal list view
                     val users = ArrayList<NaturalModel>()
                     users.add(NaturalModel("Mukesh Yadav","This is super shoes",R.drawable.watch))
                     users.add(NaturalModel("ABC Limited","You can use",R.drawable.jacket))
                     users.add(NaturalModel("XYZ LTD","I am using since 10 year",R.drawable.show_1))
                     LazyColumnDemo(LocalContext.current,users)

                     Spacer(modifier = Modifier.padding(13.dp))
                     Text(text = "Login Form ",
                         fontWeight = FontWeight.Bold,
                         fontSize = 20.sp,
                         color = red
                     )
                     LginForm()


                 }
             }
         })

}
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeholder: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        onValueChange = onChange,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = keyBoardActions,
        enabled = isEnabled,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            disabledBorderColor = Color.Gray,
            disabledTextColor = Color.Black
        ),
        placeholder = {
            Text(text = placeholder, style = TextStyle(fontSize = 18.sp, color = Color.LightGray))
        }
    )
}
@Composable
fun LginForm(){
    var viewModel=FormViewModel()
    val focusManager = LocalFocusManager.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppTextField(
            text = viewModel.firstName,
            placeholder = "First Name",
            onChange = {
                viewModel.firstName = it
            },
            imeAction = ImeAction.Next,//Show next as IME button
            keyboardType = KeyboardType.Text, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(modifier = Modifier.padding(10.dp))
        AppTextField(
            text = viewModel.password,
            placeholder = "Password",
            onChange = {
                viewModel.password = it
            },
            imeAction = ImeAction.Next,//Show next as IME button)
            keyboardType = KeyboardType.Text, //Plain text keyboard
            keyBoardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ))
        
    }
}
@Composable
fun ElectronicCategory() {
    var selectedItem by remember{ mutableStateOf( "") }
    var clicktring by remember{ mutableStateOf( "") }
    val itemList = listOf("All", "Electronics", "Mobile", "Sports","Sneakers", "Jacket", "Watch", "Watch")
    val categoryImagesList = listOf<Int>(
        R.drawable.shoe_thumb_2,
        R.drawable.jacket,
        R.drawable.watch,
        R.drawable.watch,
        R.drawable.shoe_thumb_2,
        R.drawable.jacket,
        R.drawable.watch,
        R.drawable.watch,
    )
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(itemList.size) { item ->
            Box(modifier = Modifier
                .selectable(
                    selected = selectedItem == itemList[item],
                    onClick = {
                        clicktring = itemList[item]
                        Log.d("clickcard", clicktring)
                        // navController.navigate(Screen.ProductDetailsScreen.route)


                    })
                .height(50.dp)
                .border(
                    color = if (clicktring == itemList[item]) orange else lightGrey,
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp)

                )) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Image(painter = painterResource(categoryImagesList[item]), contentDescription = "",
                        modifier = Modifier.size(30.dp, 30.dp))
                    Text(
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(
                                start = 5.dp,
                                end = 16.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            ),
                        text = itemList[item],
                        color = if (item == 0) lightblack else Color.LightGray
                    )
                }

            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}
@Composable
fun AllCategory() {
    var selectedItem by remember{ mutableStateOf( "") }
    var clicktring by remember{ mutableStateOf( "") }
    val itemList = listOf("All", "Electronics", "Mobile", "Sports","Sneakers", "Jacket", "Watch", "Watch")
    val categoryImagesList = listOf<Int>(
        R.drawable.shoe_thumb_2,
        R.drawable.jacket,
        R.drawable.watch,
        R.drawable.watch,
        R.drawable.shoe_thumb_2,
        R.drawable.jacket,
        R.drawable.watch,
        R.drawable.watch,
    )
    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(itemList.size) { item ->
            Box(modifier = Modifier
                .selectable(
                    selected = selectedItem == itemList[item],
                    onClick = {
                        clicktring = itemList[item]
                        Log.d("clickcard", clicktring)
                        // navController.navigate(Screen.ProductDetailsScreen.route)
                    })
                .height(200.dp)
                .width(200.dp)
                .border(
                    color = if (clicktring == itemList[item]) orange else lightGrey,
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp)

                )) {
                Column(modifier = Modifier
                    .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(categoryImagesList[item]), contentDescription = "",
                        modifier = Modifier.size(100.dp, 100.dp))
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(text = "Nike",
                        fontWeight = FontWeight.Bold,
                        fontSize =20.sp,
                        color = black
                    )
                    Text(text = "$400",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = black
                    )
                    Text(text = "Bike,Sports & Outdoors",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = black
                    )
                }

            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.Center
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.DarkGray)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun LazyColumnDemo(context: Context, users: ArrayList<NaturalModel>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(items = users, itemContent = { item ->
            ListItemView(context, item)
        })
    }
}

@Composable
fun ListItemView(context: Context, user: NaturalModel) : Unit {
    Card(
        backgroundColor = Color.White,
        elevation = Dp(2F),
        modifier = Modifier
            .padding(all = 5.dp)
            .clickable {
                cardViewCallBack(context, user)
            }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)) {
            user.title?.let { Text(text = it,
                modifier = Modifier.padding(all = 4.dp),
                textAlign = TextAlign.Start,
                fontSize = 22.sp,
                color = Color.Black) }
            user.desc?.let { Text(text = it,
                modifier = Modifier.padding(all = 4.dp),
                textAlign = TextAlign.Start,
                fontSize = 22.sp,
                color = Color.Blue) }

        }
    }
}

fun cardViewCallBack(context: Context, user: NaturalModel) {
    Toast.makeText(context,"Hello ${user.title}", Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun DefaultPreview() {
    LazyColumnDemo(
        LocalContext.current,
        Arrays.asList(NaturalModel("Mukesh Yadav","Jet Pack Compose",R.drawable.jacket)) as ArrayList<NaturalModel>
    )
}




