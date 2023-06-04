import { NgModule } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCommonModule } from '@angular/material/core';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatButtonModule} from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';

const materialComponents = [
  MatCommonModule,
  MatDatepickerModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatInputModule,
  MatSelectModule,
  MatCheckboxModule,
  MatButtonModule,
  MatSidenavModule,
  MatToolbarModule,
  MatDividerModule,
  MatListModule,
  MatIconModule,
  MatDialogModule,
  MatTableModule,
]

@NgModule({
  imports: [materialComponents],
  exports: [materialComponents]
})
export class MaterialModule { }
