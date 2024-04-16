package com.example.newsapp.presentation.search
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.domain.model.Article
import com.example.newsapp.presentation.Dimens
import com.example.newsapp.presentation.common.ArticlesList
import com.example.newsapp.presentation.common.SearchBar

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetail : (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = Dimens.SmallPadding, start = Dimens.SmallPadding, end = Dimens.SmallPadding)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = {article->
                    navigateToDetail(article)
                }
            )
        }
    }
}