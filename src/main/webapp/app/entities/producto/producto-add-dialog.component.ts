import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProducto } from 'app/shared/model/producto.model';
import { ProductoService } from './producto.service';

@Component({
  templateUrl: './producto-add-dialog.component.html'
})
export class ProductoAddDialogComponent {
  producto?: IProducto;

  constructor(protected productoService: ProductoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmCompra(id: number): void {
    this.activeModal.close();
  }
}
