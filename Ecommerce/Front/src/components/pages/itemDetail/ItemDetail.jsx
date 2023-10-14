/* eslint-disable no-unused-vars */
/* eslint-disable react/jsx-no-undef */
import { useContext, useEffect, useState } from "react";
import { useParams, useNavigate, Link} from "react-router-dom";
import { db } from "../../../firebaseConfig";
import { getDoc, collection, doc } from "firebase/firestore";
import { Button, IconButton } from "@mui/material";
import { CartContext } from "../../../context/CartContext";
import ShoppingCartCheckoutIcon from "@mui/icons-material/ShoppingCartCheckout";
import ShopIcon from '@mui/icons-material/Shop';


const ItemDetail = () => {
  const { id } = useParams();
  const { addToCart, getQuantityById } = useContext(CartContext);
  let quantity = getQuantityById(id);
  const [product, setProduct] = useState(null);
  const [counter, setCounter] = useState(quantity || 1);
  const navigate = useNavigate();

  useEffect(() => {
    let refCollection = collection(db, "products");
    let refDoc = doc(refCollection, id);
    getDoc(refDoc)
      .then((res) => setProduct({ ...res.data(), id: res.id }))
      .catch((error) => console.log(error));
  }, [id]);

  // SUMAR
  const addOne = () => {
    if (counter < product.stock) {
      setCounter(counter + 1);
    } else {
      alert("stock maximo");
    }
  };

  // RESTAR

  const subOne = () => {
    if (counter > 1) {
      setCounter(counter - 1);
    } else {
      alert("no podes agregar menos de 1 elemento al carrito");
    }
  };
  // AGREGAR AL CARRITO

  const onAdd = () => {
    let obj = {
      ...product,
      quantity: counter,
    };
    addToCart(obj);
  };

  return (
  <div id = 'card'className="block rounded-lg h-100 x-20 bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700">
      <div className="d-flex justify-content-center">
    <div id='carrito' className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
      <Link to={`/cart`}>
        <IconButton>
          <ShoppingCartCheckoutIcon color="primary" style={{ width: '100px', height: '100px' }} />
        </IconButton>
      </Link>
    </div>
    <div id='tienda' className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
      <Link to={`/shop`}>
        <IconButton>
          <ShopIcon color="primary" style={{ width: '100px', height: '100px' }} />
        </IconButton>
      </Link>
    </div>
  </div>
  <h5 className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50">
    {product && (
      <h1 className="text-5xl font-medium text-red-500 text-center">Total: ${product.unit_price * counter}</h1>
    )}
  </h5>
  <div>
  {product && (
  <img
  className="w-1/6 h-1/6 md:h-1/4 md:w-1/4 mx-auto rounded-full object-cover md:!rounded-none md:!rounded-l-lg"
  src={product.image}
                alt=""
              />
              )}
  </div>
  <div id='titulo' className="mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50 text-center">
    {product && (
      <h2 className="text-5xl font-medium">{product.title}</h2>
    )}
  </div>
  <p className="mb-4 text-base text-neutral-600 dark:text-neutral-200 text-center">
    {quantity && <h6>Ya tienes {quantity} en el carrito</h6>}
    {product?.stock === quantity && <h6>Ya tienes el máximo en el carrito</h6>}
  </p>
  <div id='sumarRestar' className="flex items-center justify-center">
    <div id='restar'>
      <Button variant="contained" onClick={subOne}>
        -
      </Button>
    </div>
    <h2>{counter}</h2>
    <div id='sumar'>
      <Button variant="contained" onClick={addOne}>
        +
      </Button>
    </div>
</div>
<div id='agregar' className="flex items-center justify-center">

    <Button onClick={onAdd} variant="contained" color="success" style={{ width: "380px",marginTop:'50px' }}>
      Agregar al carrito
    </Button>
  </div>
  </div>


  );
};

export default ItemDetail;
