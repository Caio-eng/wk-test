import { NavComponent } from './nav/nav.component';
import { ResultadosComponent } from './resultados/resultados.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '', component: NavComponent, children: [
    {path: 'candidatos', component: ResultadosComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
