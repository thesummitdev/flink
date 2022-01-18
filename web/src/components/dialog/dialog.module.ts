import {A11yModule} from '@angular/cdk/a11y';
import {OverlayModule} from '@angular/cdk/overlay';
import {PortalModule} from '@angular/cdk/portal';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {DirectivesModule} from 'web/src/directives/directives.module';

import {LinkFormComponent} from '../linkform/linkform.component';

import {DialogContainerComponent} from './dialog.container.component';
import {EditLinkComponent} from './editlink/editlink.dialog.component';
import {LoginDialogComponent} from './login/login.dialog.component';

@NgModule({
  declarations: [
    DialogContainerComponent,
    LoginDialogComponent,
    EditLinkComponent,
    LinkFormComponent,
  ],
  imports: [
    A11yModule,
    BrowserModule,
    BrowserAnimationsModule,
    DirectivesModule,
    FormsModule,
    OverlayModule,
    PortalModule,
  ],
})
export class DialogModule {
}
