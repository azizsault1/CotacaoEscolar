import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PesquisaCotacaoComponent } from './pesquisa-cotacao/pesquisa-cotacao.component';

const routes: Routes = [
  { path: '', component: PesquisaCotacaoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CotacaoRoutingModule { }