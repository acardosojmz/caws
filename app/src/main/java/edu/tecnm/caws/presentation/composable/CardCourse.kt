package edu.tecnm.caws.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.tecnm.caws.domain.model.Course
import edu.tecnm.caws.presentation.theme.SoftWhite

@Composable
fun CardCourse(course: Course){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
        ,
        elevation =  CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = SoftWhite,
        )
    ){
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(color = SoftWhite)
        ){
            Text(
                text = course.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp),
                text = "Periodo del ${course.initialdate} al ${course.finaldate}",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = course.description, fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Row {
                Text(
                    text = "Lugar: ${course.place}", fontSize = 10.sp,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Inscribirme", fontSize = 10.sp
                    )
                }
            }
            
        }
    }
}