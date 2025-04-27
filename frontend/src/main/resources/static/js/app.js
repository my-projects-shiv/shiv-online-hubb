// cart.js

let cart = JSON.parse(localStorage.getItem('cart')) || [];

function updateCart() {
    let cartItemsContainer = document.getElementById("cart-items");
    cartItemsContainer.innerHTML = ''; // Clear current items
    let totalPrice = 0;

    cart.forEach(item => {
        totalPrice += item.price;

        let cartItem = document.createElement('div');
        cartItem.className = 'cart-item';

        cartItem.innerHTML = `
            <img class="cart-item-img" src="./images/${item.name.toLowerCase().replace(' ', '-')}.jpg" alt="${item.name}">
            <div class="cart-item-details">
                <h3>${item.name}</h3>
                <p>Price: ₹${item.price}</p>
                <p>Quantity: ${item.quantity}</p>
            </div>
        `;
        cartItemsContainer.appendChild(cartItem);
    });

    document.getElementById("total-price").innerText = `Total Price: ₹${totalPrice}`;
}

function placeOrder() {
    // Simulating an order placement by clearing the cart and redirecting
    localStorage.removeItem('cart'); // Remove cart from local storage
    setTimeout(() => {
        window.location.href = "success.html"; // Redirect to success page
    }, 1000); // Wait for a second before redirecting
}

// To initialize the cart page with items
updateCart();

// Add the functionality to the place order button
document.getElementById('place-order-btn').addEventListener('click', placeOrder);
