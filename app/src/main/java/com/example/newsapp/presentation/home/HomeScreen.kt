package com.example.newsapp.presentation.home


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.newsapp.R
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens
import com.example.newsapp.presentation.Dimens.ExtraSmallPadding
import com.example.newsapp.presentation.Dimens.MediumPadding
import com.example.newsapp.presentation.Dimens.SmallPadding
import com.example.newsapp.presentation.common.ArticlesList
import com.example.newsapp.presentation.common.SearchBar
import com.example.newsapp.presentation.navgraph.Routs


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetail : (Article) -> Unit
) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.MediumPadding)
            .statusBarsPadding()
    ) {


        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = MediumPadding)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToSearch()
            }
        )




        Spacer(modifier = Modifier.height(MediumPadding))

        ArticlesList(
            modifier = Modifier.padding(horizontal = ExtraSmallPadding),
            articles = articles,
            onClick = {
                navigateToDetail(it)
            }
        )
    }
}