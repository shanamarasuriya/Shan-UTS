window.addEventListener('DOMContentLoaded', (event) => {
    // Get a reference to the image
    const image = document.getElementById('picanime');

    // Apply CSS animation to enlarge the image
    image.style.transition = 'transform 1s ease, opacity 1s ease';
    image.style.transform = 'scale(1.2)';
    image.style.opacity = '1';
});
