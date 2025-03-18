package com.codelab.basiclayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            MySootheApp(windowSizeClass)
        }
    }
}

/**
 * A customizable search bar composable.
 *
 * This composable provides a basic search bar with a leading search icon, a placeholder text,
 * and a filled background. It can be customized using the provided [modifier].
 *
 * @param modifier Modifier to be applied to the search bar. Allows customization of layout,
 *                 behavior, and appearance. Defaults to [Modifier].
 *
 * Example usage:
 * ```
 *  SearchBar(modifier = Modifier.padding(16.dp))
 * ```
 * This will create a search bar with 16dp padding on all sides.
 */// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {Icon(Icons.Default.Search, contentDescription = null)},
        placeholder = {Text(text = stringResource(id = R.string.placeholder_search))},
        colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.surface),
        modifier = modifier
            .heightIn(min = 56.dp)
            .fillMaxWidth()
    )
}

/**
 * Composable function that displays an image and text, aligned vertically.
 * This is used to represent a single "Align your body" element in the UI.
 *
 * @param drawable The drawable resource ID for the image to be displayed.
 * @param text The string resource ID for the text to be displayed below the image.
 * @param modifier Modifier to apply to the container column.
 */// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
    }
}

/**
 * A composable function that represents a card for a favorite collection.
 *
 * This card displays an image and a text label within a Material Surface.
 * It's designed to be used in a list of favorite collections.
 *
 * @param drawable The drawable resource ID for the image to be displayed.
 * @param text The string resource ID for the text label to be displayed.
 * @param modifier Modifier to be applied to the Surface. Allows customization of layout and behavior.
 *
 * Example Usage:
 * ```
 * FavoriteCollectionCard(
 *     drawable = R.drawable.collection_image,
 *     text = R.string.collection_name,
 *     modifier = Modifier.padding(8.dp)
 * )
 * ```
 */
// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ){
            Image(
                painterResource(id = drawable),
                modifier = Modifier.size(56.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

    }
}

/**
 * Displays a horizontal row of "Align Your Body" elements, typically representing
 * different exercise or wellness categories.
 *
 * This composable uses a [LazyRow] for efficient rendering of potentially many items.
 * It arranges the items horizontally with a fixed spacing and adds padding to the start and end.
 *
 * @param modifier [Modifier] to apply to the row. This can be used to control the
 *                 row's size, position, and appearance.
 *
 * Example Usage:
 * ```
 * AlignYourBodyRow(modifier = Modifier.fillMaxWidth())
 * ```
 *
 * The elements displayed in this row are determined by the `alignYourBodyData` list.
 * Each item is displayed using the `AlignYourBodyElement` composable.
 *
 * The row uses the following:
 * *   **Arrangement.spacedBy(8.dp):**  Specifies that each element will have an 8dp
 *     space between it and its adjacent element.
 * *   **PaddingValues(horizontal = 16.dp):**  Adds 16dp padding to the left and right
 *     edges of the row. This means the first and last elements will be 16dp away from
 *     the screen edges.
 */// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp), //spacing between items
        contentPadding = PaddingValues(horizontal = 16.dp), //spacing between first and last item
        modifier = modifier
    ) {
        items(alignYourBodyData.size) { item ->
            AlignYourBodyElement(
                drawable = alignYourBodyData[item].drawable,
                text = alignYourBodyData[item].text
            ) //item is the index of the item in the list
        }
    }
}

/**
 * Displays a horizontal grid of favorite collection cards.
 *
 * This composable function uses a `LazyHorizontalGrid` to efficiently display a grid of
 * `FavoriteCollectionCard` composables. The grid is configured with a fixed number of rows
 * and specific padding and spacing between items.
 *
 * @param modifier The modifier to be applied to the grid.
 *                 Defaults to `Modifier`.
 *
 * @see LazyHorizontalGrid
 * @see GridCells
 * @see PaddingValues
 * @see Arrangement
 * @see FavoriteCollectionCard
 */// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(count = 2), //number of rows
        contentPadding = PaddingValues(horizontal = 16.dp), //spacing between first and last item
        horizontalArrangement = Arrangement.spacedBy(8.dp), //spacing between items in the row
        verticalArrangement = Arrangement.spacedBy(8.dp), //spacing between items in the column
        modifier = modifier.height(120.dp)
    ) {
        items(favoriteCollectionsData.size) { item ->
            FavoriteCollectionCard(
                drawable = favoriteCollectionsData[item].drawable,
                text = favoriteCollectionsData[item].text
            )
        }
    }

}

/**
 * Composable function that represents a section on the Home screen.
 *
 * This function provides a structured layout for a section with a title and content.
 * It uses a slot-based API to allow for flexible content within the section.
 *
 * @param title The string resource ID for the section's title. This will be displayed in uppercase.
 * @param modifier The [Modifier] to be applied to the outer [Column] container.
 * @param content A composable lambda that defines the content to be displayed within the section, below the title.
 *
 * Example Usage:
 * ```
 * HomeSection(
 *     title = R.string.my_section_title,
 *     modifier = Modifier.fillMaxWidth()
 * ) {
 *     // Your section content here
 *     Image(painter = painterResource(id = R.drawable.my_image), contentDescription = "My Image")
 *     Text("Some text content")
 * }
 * ```
 */
// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        Text(
            stringResource(title).uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp) //spacing between text and image top and bottom
                .padding(horizontal = 16.dp) //spacing between text and image and between items in the row
        )
        content()
    }
}

/**
 * Composable function representing the main home screen of the application.
 *
 * This screen displays a scrollable list of content, including a search bar,
 * "Align Your Body" section, and "Favorite Collections" section.
 *
 * The content is arranged in a vertical column with spacing and padding for
 * improved visual appeal. The column is scrollable to accommodate content
 * that exceeds the screen's height.
 *
 * @param modifier Modifier to be applied to the root column. This allows
 *                 for customization of the layout, such as adding padding or
 *                 setting background color. Defaults to an empty Modifier.
 */
// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState()) //scrolls the content of the column if it exceeds the screen height
    ) {
        Spacer(Modifier.height(16.dp)) //spacing between top and first item in the home screen column and top of the screen
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp)) //spacing between last item in the home screen column and bottom of the screen
    }
}

/**
 * A custom bottom navigation bar for the Soothe app.
 *
 * This composable function displays a bottom navigation bar with two items:
 * "Home" (with a spa icon) and "Profile" (with an account circle icon).
 * It uses Material Design 3's `NavigationBar` and `NavigationBarItem` components.
 *
 * @param modifier Modifier to be applied to the navigation bar.
 */
// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(id = R.string.bottom_navigation_home))
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(id = R.string.bottom_navigation_profile))
            }
        )
    }
}

/**
 *  [MySootheAppPortrait] is the main composable function that represents the portrait layout
 *  of the MySoothe application. It uses the MySoothe theme and structures the screen with a
 *  bottom navigation bar and a home screen content area.
 *
 *  This function is designed to be used for screens in portrait mode.
 *
 *  The function does the following:
 *
 *  1. Applies the [MySootheTheme] to ensure consistent styling across the application.
 *  2. Uses the [Scaffold] composable to structure the screen layout.
 *     - Sets the [SootheBottomNavigation] as the bottom navigation bar.
 *     - Provides padding to the main content area to accommodate the bottom navigation bar.
 *  3. Displays the [HomeScreen] composable within the scaffold's content area, ensuring it's padded
 *     correctly.
 *
 *  Example usage:
 *
 *  ```
 *  @Composable
 *  fun MyScreen() {
 *      MySootheAppPortrait()
 *  }
 *  ```
 *
 */
@Composable
fun MySootheAppPortrait() {
    MySootheTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

/**
 * A custom NavigationRail composable for the Soothe app.
 *
 * This composable displays a vertical navigation rail with two items:
 * - A "Home" item represented by a spa icon.
 * - A "Profile" item represented by an account circle icon.
 *
 * The rail is styled with the app's background color and has padding on the start and end.
 * The navigation items are vertically centered within the rail.
 *
 * Currently, the "Home" item is selected by default, and clicking on any item does nothing.
 *
 * @param modifier Modifier to be applied to the NavigationRail.
 */
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(start = 8.dp, end = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = true,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = false,
                onClick = {}
            )
        }
    }
}


/**
 * `MySootheAppLandscape` is a composable function that represents the main layout of the MySoothe application in landscape orientation.
 *
 * It provides the overall structure for the landscape view, including a navigation rail on the left and the main home screen content on the right.
 *
 * This function uses the `MySootheTheme` for consistent styling and theming throughout the application.
 *
 * The layout is structured using a `Row` to arrange the navigation rail and the home screen horizontally.
 *
 * @see SootheNavigationRail
 * @see HomeScreen
 * @see MySootheTheme
 *
 * Example usage:
 * ```
 *  MySootheAppLandscape()
 * ```
 */
@Composable
fun MySootheAppLandscape() {
    MySootheTheme {
        Row {
            SootheNavigationRail()
            HomeScreen()
        }
    }
}

/**
 * Main composable function for the MySoothe app.
 *
 * This function determines the layout based on the window's width size class.
 * It switches between a portrait layout ([MySootheAppPortrait]) for compact screens
 * and a landscape layout ([MySootheAppLandscape]) for expanded screens.
 *
 * @param windowSize The [WindowSizeClass] object representing the current window's size.
 */
@Composable
fun MySootheApp(windowSize: WindowSizeClass) {
//    Scaffold (
//        bottomBar = { SootheBottomNavigation() }
//    ) { padding ->
//        HomeScreen(Modifier.padding(padding))
//    }
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MySootheAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MySootheAppLandscape()
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            drawable = R.drawable.ab1_inversions,
            text = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    MySootheTheme {
        FavoriteCollectionCard(
            drawable = R.drawable.fc2_nature_meditations,
            text = R.string.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 180)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}
